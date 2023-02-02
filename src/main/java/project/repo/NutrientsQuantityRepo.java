package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.NutrientsQuantity;

import java.util.List;

public interface NutrientsQuantityRepo extends CrudRepository<NutrientsQuantity, Integer> {
    List<NutrientsQuantity> getAllByOwner(String owner);

    void deleteAllByOwner(String owner);
}
