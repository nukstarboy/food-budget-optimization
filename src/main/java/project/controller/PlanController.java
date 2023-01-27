package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.Food;
import project.repo.FoodRepo;

@RestController
public class PlanController {

    private final FoodRepo foodRepo;

    @Autowired
    public PlanController(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }

    @GetMapping
    public Iterable<Food> get() {
        return this.foodRepo.findAll();
    }

    @PostMapping("/quiz/questions")
    public Food add() {
        return this.foodRepo.save(new Food());
    }
}
