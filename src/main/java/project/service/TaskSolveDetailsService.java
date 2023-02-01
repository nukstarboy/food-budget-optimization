package project.service;

import org.springframework.stereotype.Service;
import project.models.TaskSolveDetails;
import project.repo.TaskSolveDetailsRepo;

@Service
public class TaskSolveDetailsService {
    private final TaskSolveDetailsRepo taskSolveDetailsRepo;

    public TaskSolveDetailsService(TaskSolveDetailsRepo taskSolveDetailsRepo) {
        this.taskSolveDetailsRepo = taskSolveDetailsRepo;
    }

    public void save(TaskSolveDetails taskSolveDetails) {
        this.taskSolveDetailsRepo.save(taskSolveDetails);
    }
}
