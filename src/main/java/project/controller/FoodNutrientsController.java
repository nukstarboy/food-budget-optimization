package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.FoodNutrients;
import project.service.FoodNutrientsService;

import java.util.List;

@RestController
@RequestMapping("/food-nutrients")
public class FoodNutrientsController {

    private final FoodNutrientsService foodNutrientsService;

    @Autowired
    public FoodNutrientsController(FoodNutrientsService foodNutrientsService) {
        this.foodNutrientsService = foodNutrientsService;
    }

    @PostMapping("/save")
    public void save(@RequestBody List<FoodNutrients> foodNutrients) {
        this.foodNutrientsService.save(foodNutrients);
    }

    @PostMapping("/exists")
    public boolean exists(@RequestBody String owner) {
        return this.foodNutrientsService.exists(owner);
    }

}
