package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/save-plan/{isToggleTriggered}")
    public void savePersonalPlan(@RequestBody PersonalQuestions personalQuestions, @PathVariable boolean isToggleTriggered) throws Exception {
        planService.savePersonalPlan(personalQuestions, isToggleTriggered);
    }

    @PostMapping("/save-family-plan/{isToggleTriggered}")
    public void saveFamilyPlan(@RequestBody List<FamilyQuestions> familyQuestions, @PathVariable boolean isToggleTriggered) {
        planService.saveFamilyPlan(familyQuestions, isToggleTriggered);
    }
}
