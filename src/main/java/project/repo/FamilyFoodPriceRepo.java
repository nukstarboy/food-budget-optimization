package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.FamilyFoodPrice;

public interface FamilyFoodPriceRepo extends CrudRepository<FamilyFoodPrice, Integer> {
}
