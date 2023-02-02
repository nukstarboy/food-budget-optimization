package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.TaskSolveDetails;

import java.util.List;

public interface TaskSolveDetailsRepo extends CrudRepository<TaskSolveDetails, Integer> {
    List<TaskSolveDetails> getAllByOwner(String owner);
}
