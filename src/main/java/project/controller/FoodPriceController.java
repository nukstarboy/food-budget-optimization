package project.controller;

import org.springframework.web.bind.annotation.*;
import project.models.FamilyFoodPrice;
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

    @PostMapping("/getFamilyFoodPrices/{owner}")
    public List<FamilyFoodPrice> getFamilyFoodPricesByMember(@PathVariable String owner,
                                                             @RequestBody String memberNames) {
        return this.foodPriceService.getByMemberNames(owner, memberNames);
    }
}
