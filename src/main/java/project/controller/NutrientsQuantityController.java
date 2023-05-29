package project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.FamilyNutrientsQuantity;
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
    public List<NutrientsQuantity> getAllNutrientsQuantityByOwner(@RequestBody String owner) {
        return this.nutrientsQuantityService.getAllNutrientsQuantityByOwner(owner);
    }

    @PostMapping("/get-all-family-nutrients")
    public List<FamilyNutrientsQuantity> getAllFamilyNutrientsQuantityByMembers(@RequestBody String members) {
        return this.nutrientsQuantityService.getAllFamilyNutrientsQuantityByMembers(members);
    }
}
