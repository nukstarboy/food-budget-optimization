package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.FamilyQuestions;
import project.models.PersonalQuestions;
import project.service.PlanService;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/save-plan")
    public void savePersonalPlan(@RequestBody PersonalQuestions personalQuestions) {
        planService.savePersonalPlan(personalQuestions);
    }

    @PostMapping("/save-family-plan")
    public void saveFamilyPlan(@RequestBody List<FamilyQuestions> familyQuestions) {
        planService.saveFamilyPlan(familyQuestions);
    }
}
