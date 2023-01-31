package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.models.Food;
import project.models.PersonalQuestions;
import project.repo.FoodRepo;
import project.service.PlanService;

@RestController
public class PlanController {
    private final FoodRepo foodRepo;
    private final PlanService planService;

    @Autowired
    public PlanController(FoodRepo foodRepo, PlanService planService) {
        this.foodRepo = foodRepo;
        this.planService = planService;
    }

    @GetMapping
    public Iterable<Food> get() {
        return this.foodRepo.findAll();
    }

    @PostMapping("/save-plan")
    public void savePersonalPlan(@RequestBody PersonalQuestions personalQuestions) {
        planService.savePersonalPlan(personalQuestions);
    }

    @PostMapping("/quiz/questions")
    public Food add() {
        return this.foodRepo.save(new Food());
    }
}
