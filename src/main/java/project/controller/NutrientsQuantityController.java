package project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.NutrientsQuantity;
import project.service.NutrientsQuantityService;

import java.util.List;

@RestController
@RequestMapping("/nutrients-quantity")
public class NutrientsQuantityController {
    private final NutrientsQuantityService nutrientsQuantityService;

    public NutrientsQuantityController(NutrientsQuantityService nutrientsQuantityService) {
        this.nutrientsQuantityService = nutrientsQuantityService;
    }

    @PostMapping
    public List<NutrientsQuantity> getAllFoodPricesByOwner(@RequestBody String owner) {
        return this.nutrientsQuantityService.getAllNutrientsQuantityByOwner(owner);
    }
}
