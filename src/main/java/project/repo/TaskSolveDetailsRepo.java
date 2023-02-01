package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.TaskSolveDetails;

public interface TaskSolveDetailsRepo extends CrudRepository<TaskSolveDetails, Integer> {
}
