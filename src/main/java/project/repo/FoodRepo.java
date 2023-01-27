package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.Food;

public interface FoodRepo extends CrudRepository<Food, Integer> {
}
