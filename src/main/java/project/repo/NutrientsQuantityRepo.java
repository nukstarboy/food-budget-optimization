package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.NutrientsQuantity;

public interface NutrientsQuantityRepo extends CrudRepository<NutrientsQuantity, Integer> {
}
