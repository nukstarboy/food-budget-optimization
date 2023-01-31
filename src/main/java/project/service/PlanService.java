package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Food;
import project.models.Nutrient;
import project.models.PersonalQuestions;
import project.utils.FoodInfo;
import project.utils.NutrientCalculator;
import project.utils.StiglerDiet;

import java.util.List;

@Service
public class PlanService {
    private final FoodInfo foodInfo;
    private final NutrientCalculator nutrientCalculator;
    private final StiglerDiet stiglerDiet;

    @Autowired
    public PlanService(FoodInfo foodInfo, NutrientCalculator nutrientCalculator, StiglerDiet stiglerDiet) {
        this.foodInfo = foodInfo;
        this.nutrientCalculator = nutrientCalculator;
        this.stiglerDiet = stiglerDiet;
    }

    public void savePersonalPlan(PersonalQuestions personalQuestions) {
        List<Nutrient> nutrients = this.nutrientCalculator.addNutrients();
        List<Food> food = this.foodInfo.addFoodInfo();
        this.stiglerDiet.resolveStigleDiet(nutrients, food);
    }
}
