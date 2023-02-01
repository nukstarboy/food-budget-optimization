package project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.FoodPrice;
import project.service.FoodPriceService;

import java.util.List;

@RestController
@RequestMapping("/food-price")
public class FoodPriceController {
    private final FoodPriceService foodPriceService;

    public FoodPriceController(FoodPriceService foodPriceService) {
        this.foodPriceService = foodPriceService;
    }

    @PostMapping
    public List<FoodPrice> getAllFoodPricesByOwner(@RequestBody String owner) {
        return this.foodPriceService.getAllFoodPricesByOwner(owner);
    }
}
