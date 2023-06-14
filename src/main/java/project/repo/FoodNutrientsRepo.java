package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.FoodNutrients;

import java.util.List;

public interface FoodNutrientsRepo extends CrudRepository<FoodNutrients, Integer> {
    List<FoodNutrients> findAllByOwner(String owner);
}
