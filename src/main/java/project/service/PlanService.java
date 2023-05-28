package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.*;
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

    @Autowired
    public PlanService(StiglerDiet stiglerDiet, NutrientCalculator nutrientCalculator, FoodPriceService foodPriceService, NutrientsQuantityService nutrientsQuantityService, TaskSolveDetailsService taskSolveDetailsService, AdminService adminService) {
        this.stiglerDiet = stiglerDiet;
        this.nutrientCalculator = nutrientCalculator;
        this.foodPriceService = foodPriceService;
        this.nutrientsQuantityService = nutrientsQuantityService;
        this.taskSolveDetailsService = taskSolveDetailsService;
        this.adminService = adminService;
    }

    public void savePersonalPlan(PersonalQuestions personalQuestions) {
        clearPersonalPlanIfExists(personalQuestions.planOwner);
        List<Nutrient> nutrients = nutrientCalculator.addNutrients(personalQuestions.age,
                personalQuestions.gender,
                personalQuestions.weight,
                personalQuestions.height,
                personalQuestions.activity,
                personalQuestions.bodyType);
        saveFoodPrices(personalQuestions, nutrients);
        saveNutrientsQuantities(personalQuestions, nutrients);
        saveTaskSolveDetails(personalQuestions, nutrients);
    }

    public void saveFamilyPlan(List<FamilyQuestions> familyQuestions) {
        clearFamilyPlanIfExists(familyQuestions.get(0).planOwner);
        List<String> familyMembers = new ArrayList<>();
        familyQuestions.forEach(questions -> {
            List<Nutrient> nutrients = nutrientCalculator.addNutrients(questions.age,
                    questions.gender,
                    questions.weight,
                    questions.height,
                    questions.activity,
                    questions.bodyType);
            familyMembers.add(questions.memberName);
            saveFamilyFoodPrices(questions, nutrients);
            saveFamilyNutrientsQuantities(questions, nutrients);
        });
        AdminDetail adminDetail = adminService.get(familyQuestions.get(0).planOwner);
        adminDetail.setFamilyMembers(String.join(", ", familyMembers));
        adminService.saveAdminDetail(adminDetail);
    }

    private void saveFamilyNutrientsQuantities(FamilyQuestions familyQuestions, List<Nutrient> nutrients) {
        List<NutrientsQuantity> nutrientsQuantities = this.stiglerDiet.nutrientsQuantities(nutrients, familyQuestions.planPeriod, familyQuestions.planOwner);
        List<FamilyNutrientsQuantity> familyNutrientsQuantities = nutrientsQuantities.stream().map((nutrient -> new FamilyNutrientsQuantity(
                familyQuestions.memberName,
                nutrient.nutrientName,
                nutrient.normalQuantityPerDay,
                nutrient.minimumQuantityPerDay,
                familyQuestions.planOwner))
        ).toList();
        this.nutrientsQuantityService.saveAllFamilyNutrients(familyNutrientsQuantities);
    }

    private void saveFamilyFoodPrices(FamilyQuestions familyQuestions, List<Nutrient> nutrients) {
        List<FoodPrice> foodPrices = this.stiglerDiet.foodPrices(nutrients, familyQuestions.planPeriod, familyQuestions.planOwner);
        List<FamilyFoodPrice> familyFoodPrices = foodPrices.stream().map((foodPrice -> new FamilyFoodPrice(
                familyQuestions.memberName,
                foodPrice.food,
                foodPrice.optimalPrice,
                foodPrice.planPeriod,
                familyQuestions.planOwner))
        ).toList();
        this.foodPriceService.saveAllFamilyFoodPrices(familyFoodPrices);
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
        List<TaskSolveDetails> taskSolveDetails = this.taskSolveDetailsService.getTaskSolveDetails(planOwner);
        if (taskSolveDetails.size() > 0) {
            this.taskSolveDetailsService.delete(planOwner);
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
    }

    private void saveFoodPrices(PersonalQuestions personalQuestions, List<Nutrient> nutrients) {
        List<FoodPrice> foodPrices = this.stiglerDiet.foodPrices(nutrients, personalQuestions.planPeriod, personalQuestions.planOwner);
        this.foodPriceService.saveAll(foodPrices);
    }

    private void saveNutrientsQuantities(PersonalQuestions personalQuestions, List<Nutrient> nutrients) {
        List<NutrientsQuantity> nutrientsQuantities = this.stiglerDiet.nutrientsQuantities(nutrients, personalQuestions.planPeriod, personalQuestions.planOwner);
        this.nutrientsQuantityService.saveAll(nutrientsQuantities);
    }

    private void saveTaskSolveDetails(PersonalQuestions personalQuestions, List<Nutrient> nutrients) {
        double optimalAnnualPrice = this.stiglerDiet.optimalPrice(nutrients, personalQuestions.planPeriod);
        double problemSolvedTime = this.stiglerDiet.problemSolvedTime(nutrients);
        long problemSolvedIterations = this.stiglerDiet.problemSolvedIterations(nutrients);
        String planPeriod = this.stiglerDiet.setPlanPeriod(personalQuestions.planPeriod);
        TaskSolveDetails taskSolveDetails = new TaskSolveDetails(optimalAnnualPrice, problemSolvedTime, problemSolvedIterations, personalQuestions.planOwner, planPeriod);
        this.taskSolveDetailsService.save(taskSolveDetails);
    }
}
