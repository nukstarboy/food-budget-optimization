package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.FoodPrice;

import java.util.List;

public interface FoodPriceRepo extends CrudRepository<FoodPrice, Integer> {
    List<FoodPrice> getFoodPricesByOwner(String owner);
}
