package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.FoodPrice;

public interface FoodPriceRepo extends CrudRepository<FoodPrice, Integer> {
}
