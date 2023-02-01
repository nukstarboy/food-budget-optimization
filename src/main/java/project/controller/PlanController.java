package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.PersonalQuestions;
import project.service.PlanService;

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
}
