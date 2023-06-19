package project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.models.TaskSolveDetails;
import project.service.TaskSolveDetailsService;

import java.util.List;

@RestController
@RequestMapping("/task-solve")
public class TaskSolveController {
    private final TaskSolveDetailsService taskSolveDetailsService;

    public TaskSolveController(TaskSolveDetailsService taskSolveDetailsService) {
        this.taskSolveDetailsService = taskSolveDetailsService;
    }

    @PostMapping("/personal")
    public List<TaskSolveDetails> getTaskSolveDetails(@RequestBody String owner) {
        return this.taskSolveDetailsService.getTaskSolveDetails(owner, "Personal");
    }

    @PostMapping("/family")
    public List<TaskSolveDetails> getFamilyTaskSolveDetails(@RequestBody String owner) {
        return this.taskSolveDetailsService.getTaskSolveDetails(owner, "Family");
    }
}
