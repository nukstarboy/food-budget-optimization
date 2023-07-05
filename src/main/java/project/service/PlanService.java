package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.*;
import project.utils.FoodInfo;
import project.utils.NutrientCalculator;
import project.utils.StiglerDiet;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {
    private final StiglerDiet stiglerDiet;
    private final NutrientCalculator nutrientCalculator;
    private final FoodPriceService foodPriceService;
    private final NutrientsQuantityService nutrientsQuantityService;
    private final TaskSolveDetailsService taskSolveDetailsService;
    private final AdminService adminService;
    private final FoodInfo foodInfo;
    private final FoodNutrientsService foodNutrientsService;

    @Autowired
    public PlanService(StiglerDiet stiglerDiet, NutrientCalculator nutrientCalculator, FoodPriceService foodPriceService, NutrientsQuantityService nutrientsQuantityService, TaskSolveDetailsService taskSolveDetailsService, AdminService adminService, FoodInfo foodInfo, FoodNutrientsService foodNutrientsService) {
        this.stiglerDiet = stiglerDiet;
        this.nutrientCalculator = nutrientCalculator;
        this.foodPriceService = foodPriceService;
        this.nutrientsQuantityService = nutrientsQuantityService;
        this.taskSolveDetailsService = taskSolveDetailsService;
        this.adminService = adminService;
        this.foodInfo = foodInfo;
        this.foodNutrientsService = foodNutrientsService;
    }

    public void savePersonalPlan(PersonalQuestions personalQuestions, boolean isToggleTriggered) throws Exception {
        clearPersonalPlanIfExists(personalQuestions.planOwner);
        List<Nutrient> nutrients = nutrientCalculator.addNutrients(personalQuestions.age,
                personalQuestions.gender,
                personalQuestions.weight,
                personalQuestions.height,
                personalQuestions.activity,
                personalQuestions.bodyType);
        saveFoodPrices(personalQuestions, nutrients, isToggleTriggered);
        saveNutrientsQuantities(personalQuestions, nutrients, isToggleTriggered);
        saveTaskSolveDetails(personalQuestions, nutrients, isToggleTriggered);
    }

    public void saveFamilyPlan(List<FamilyQuestions> familyQuestions, boolean isToggleTriggered) {
        clearFamilyPlanIfExists(familyQuestions.get(0).planOwner);
        List<String> familyMembers = new ArrayList<>();
        familyQuestions.forEach(questions -> {
            List<Nutrient> nutrients = null;
            try {
                nutrients = nutrientCalculator.addNutrients(questions.age,
                        questions.gender,
                        questions.weight,
                        questions.height,
                        questions.activity,
                        questions.bodyType);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            familyMembers.add(questions.memberName);
            try {
                saveFamilyFoodPrices(questions, nutrients, isToggleTriggered);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                saveFamilyNutrientsQuantities(questions, nutrients, isToggleTriggered);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                saveFamilyOptimalBudget(questions, nutrients, isToggleTriggered);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        AdminDetail adminDetail = adminService.get(familyQuestions.get(0).planOwner);
        adminDetail.setFamilyMembers(String.join(", ", familyMembers));
        adminService.save(adminDetail);
    }

    private void saveFamilyNutrientsQuantities(FamilyQuestions familyQuestions, List<Nutrient> nutrients, boolean isToggleTriggered) throws Exception {
        List<NutrientsQuantity> nutrientsQuantities = new ArrayList<>();
        if (isToggleTriggered) {
            List<FoodNutrients> allByOwner = this.foodNutrientsService.findAllByOwner(familyQuestions.planOwner);
            List<Food> food = mapToFood(allByOwner);
            nutrientsQuantities = this.stiglerDiet.nutrientsQuantities(nutrients, familyQuestions.planPeriod, familyQuestions.planOwner, food);
        } else {
            nutrientsQuantities = this.stiglerDiet.nutrientsQuantities(nutrients, familyQuestions.planPeriod, familyQuestions.planOwner, foodInfo.addFoodInfo());
        }
        List<FamilyNutrientsQuantity> familyNutrientsQuantities = nutrientsQuantities.stream().map((nutrient -> new FamilyNutrientsQuantity(
                familyQuestions.memberName,
                nutrient.nutrientName,
                nutrient.normalQuantityPerDay,
                nutrient.minimumQuantityPerDay,
                familyQuestions.planOwner))
        ).toList();
        this.nutrientsQuantityService.saveAllFamilyNutrients(familyNutrientsQuantities);
    }

    private void saveFamilyFoodPrices(FamilyQuestions familyQuestions, List<Nutrient> nutrients, boolean isToggleTriggered) throws Exception {
        List<FoodPrice> foodPrices = new ArrayList<>();
        if (isToggleTriggered) {
            List<FoodNutrients> allByOwner = this.foodNutrientsService.findAllByOwner(familyQuestions.planOwner);
            List<Food> food = mapToFood(allByOwner);
            foodPrices = this.stiglerDiet.foodPrices(nutrients, familyQuestions.planPeriod, familyQuestions.planOwner, food);
        } else {
            foodPrices = this.stiglerDiet.foodPrices(nutrients, familyQuestions.planPeriod, familyQuestions.planOwner, foodInfo.addFoodInfo());
        }
        List<FamilyFoodPrice> familyFoodPrices = foodPrices.stream().map((foodPrice -> new FamilyFoodPrice(
                familyQuestions.memberName,
                foodPrice.food,
                foodPrice.optimalPrice,
                foodPrice.planPeriod,
                familyQuestions.planOwner))
        ).toList();
        this.foodPriceService.saveAllFamilyFoodPrices(familyFoodPrices);
    }

    private List<Food> mapToFood(List<FoodNutrients> allByOwner) {
        return allByOwner.stream().map(foodNutrients -> {
            double[] ingredients = new double[]{
                    foodNutrients.getCalories(),
                    foodNutrients.getProtein(),
                    foodNutrients.getCalcium(),
                    foodNutrients.getIron(),
                    foodNutrients.getVitaminA(),
                    foodNutrients.getThiamine(),
                    foodNutrients.getRiboflavin(),
                    foodNutrients.getNiacin(),
                    foodNutrients.getAscorbicAcid()
            };
            return new Food(foodNutrients.getFoodName(), ingredients);
        }).toList();
    }

    private void clearPersonalPlanIfExists(String planOwner) {
        List<FoodPrice> allFoodPricesByOwner = this.foodPriceService.getAllFoodPricesByOwner(planOwner);
        if (allFoodPricesByOwner.size() > 0) {
            this.foodPriceService.deleteAll(planOwner);
        }
        List<NutrientsQuantity> allNutrientsQuantityByOwner = this.nutrientsQuantityService.getAllNutrientsQuantityByOwner(planOwner);
        if (allNutrientsQuantityByOwner.size() > 0) {
            this.nutrientsQuantityService.deleteAll(planOwner);
        }
        List<TaskSolveDetails> taskSolveDetails = this.taskSolveDetailsService.getTaskSolveDetails(planOwner, "Personal");
        if (taskSolveDetails.size() > 0) {
            this.taskSolveDetailsService.deleteAll(taskSolveDetails);
        }
    }

    private void clearFamilyPlanIfExists(String planOwner) {
        List<FamilyFoodPrice> allFoodPricesByOwner = this.foodPriceService.getFamilyFoodPricesByOwner(planOwner);
        if (allFoodPricesByOwner.size() > 0) {
            this.foodPriceService.deleteFamilyFoodPrices(planOwner);
        }
        List<FamilyNutrientsQuantity> allNutrientsQuantityByOwner = this.nutrientsQuantityService.getFamilyNutrientsQuantityByOwner(planOwner);
        if (allNutrientsQuantityByOwner.size() > 0) {
            this.nutrientsQuantityService.deleteFamilyNutrientsQuantity(planOwner);
        }
        List<TaskSolveDetails> taskSolveDetails = this.taskSolveDetailsService.getTaskSolveDetails(planOwner, "Family");
        if (taskSolveDetails.size() > 0) {
            this.taskSolveDetailsService.deleteAll(taskSolveDetails);
        }
    }

    private void saveFoodPrices(PersonalQuestions personalQuestions, List<Nutrient> nutrients, boolean isToggleTriggered) throws Exception {
        List<FoodPrice> foodPrices = new ArrayList<>();
        if (isToggleTriggered) {
            List<FoodNutrients> allByOwner = this.foodNutrientsService.findAllByOwner(personalQuestions.planOwner);
            List<Food> food = mapToFood(allByOwner);
            foodPrices = this.stiglerDiet.foodPrices(nutrients, personalQuestions.planPeriod, personalQuestions.planOwner, food);
        } else {
            foodPrices = this.stiglerDiet.foodPrices(nutrients, personalQuestions.planPeriod, personalQuestions.planOwner, foodInfo.addFoodInfo());
        }
        this.foodPriceService.saveAll(foodPrices);
    }

    private void saveNutrientsQuantities(PersonalQuestions personalQuestions, List<Nutrient> nutrients, boolean isToggleTriggered) throws Exception {
        List<NutrientsQuantity> nutrientsQuantities = new ArrayList<>();
        if (isToggleTriggered) {
            List<FoodNutrients> allByOwner = this.foodNutrientsService.findAllByOwner(personalQuestions.planOwner);
            List<Food> food = mapToFood(allByOwner);
            nutrientsQuantities = this.stiglerDiet.nutrientsQuantities(nutrients, personalQuestions.planPeriod, personalQuestions.planOwner, food);
        } else {
            nutrientsQuantities = this.stiglerDiet.nutrientsQuantities(nutrients, personalQuestions.planPeriod, personalQuestions.planOwner, foodInfo.addFoodInfo());
        }
        this.nutrientsQuantityService.saveAll(nutrientsQuantities);
    }

    private void saveTaskSolveDetails(PersonalQuestions personalQuestions, List<Nutrient> nutrients, boolean isToggleTriggered) throws Exception {
        double optimalAnnualPrice;
        double problemSolvedTime;
        long problemSolvedIterations;
        if (isToggleTriggered) {
            List<FoodNutrients> allByOwner = this.foodNutrientsService.findAllByOwner(personalQuestions.planOwner);
            List<Food> personalFood = mapToFood(allByOwner);
            optimalAnnualPrice = this.stiglerDiet.optimalPrice(nutrients, personalQuestions.planPeriod, personalFood);
            problemSolvedTime = this.stiglerDiet.problemSolvedTime(nutrients, personalFood);
            problemSolvedIterations = this.stiglerDiet.problemSolvedIterations(nutrients, personalFood);
        } else {
            optimalAnnualPrice = this.stiglerDiet.optimalPrice(nutrients, personalQuestions.planPeriod, foodInfo.addFoodInfo());
            problemSolvedTime = this.stiglerDiet.problemSolvedTime(nutrients, foodInfo.addFoodInfo());
            problemSolvedIterations = this.stiglerDiet.problemSolvedIterations(nutrients, foodInfo.addFoodInfo());
        }
        String planPeriod = this.stiglerDiet.setPlanPeriod(personalQuestions.planPeriod);
        TaskSolveDetails taskSolveDetails = new TaskSolveDetails(optimalAnnualPrice, problemSolvedTime, problemSolvedIterations, personalQuestions.planOwner, planPeriod, "Personal", null);
        this.taskSolveDetailsService.save(taskSolveDetails);
    }

    private void saveFamilyOptimalBudget(FamilyQuestions questions, List<Nutrient> nutrients, boolean isToggleTriggered) throws Exception {
        double optimalAnnualPrice;
        if (isToggleTriggered) {
            List<FoodNutrients> allByOwner = this.foodNutrientsService.findAllByOwner(questions.planOwner);
            List<Food> personalFood = mapToFood(allByOwner);
            optimalAnnualPrice = this.stiglerDiet.optimalPrice(nutrients, questions.planPeriod, personalFood);
        } else {
            optimalAnnualPrice = this.stiglerDiet.optimalPrice(nutrients, questions.planPeriod, foodInfo.addFoodInfo());
        }
        String planPeriod = this.stiglerDiet.setPlanPeriod(questions.planPeriod);
        TaskSolveDetails taskSolveDetails = new TaskSolveDetails(optimalAnnualPrice, null, null, questions.planOwner, planPeriod, "Family", questions.memberName);
        this.taskSolveDetailsService.save(taskSolveDetails);
    }
}
