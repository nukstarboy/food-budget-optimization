package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.FamilyNutrientsQuantity;

import java.util.List;

public interface FamilyNutrientsQuantityRepo extends CrudRepository<FamilyNutrientsQuantity, Integer> {
    List<FamilyNutrientsQuantity> getFamilyNutrientsQuantitiesByOwner(String owner);

    void deleteAllByOwner(String owner);
}
