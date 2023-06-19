package project.service;

import org.springframework.stereotype.Service;
import project.models.TaskSolveDetails;
import project.repo.TaskSolveDetailsRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskSolveDetailsService {
    private final TaskSolveDetailsRepo taskSolveDetailsRepo;

    public TaskSolveDetailsService(TaskSolveDetailsRepo taskSolveDetailsRepo) {
        this.taskSolveDetailsRepo = taskSolveDetailsRepo;
    }

    public List<TaskSolveDetails> getTaskSolveDetails(String owner, String planType) {
        return this.taskSolveDetailsRepo.getAllByOwnerAndPlanType(owner, planType);
    }

    public void save(TaskSolveDetails taskSolveDetails) {
        this.taskSolveDetailsRepo.save(taskSolveDetails);
    }

    @Transactional
    public void deleteAll(List<TaskSolveDetails> taskSolves) {
        this.taskSolveDetailsRepo.deleteAll(taskSolves);
    }
}
