package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.FoodNutrients;
import project.repo.FoodNutrientsRepo;

import java.util.List;

@Service
public class FoodNutrientsService {

    private final FoodNutrientsRepo foodNutrientsRepo;

    @Autowired
    public FoodNutrientsService(FoodNutrientsRepo foodNutrientsRepo) {
        this.foodNutrientsRepo = foodNutrientsRepo;
    }

    public void save(List<FoodNutrients> foodNutrients) {
        List<FoodNutrients> found = foodNutrientsRepo.findAllByOwner(foodNutrients.get(0).getOwner());
        if (found.isEmpty()) {
            foodNutrientsRepo.saveAll(foodNutrients);
        }
        foodNutrientsRepo.deleteAll(found);
        foodNutrientsRepo.saveAll(foodNutrients);
    }
}
