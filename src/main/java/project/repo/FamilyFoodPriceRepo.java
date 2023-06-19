package project.repo;

import org.springframework.data.repository.CrudRepository;
import project.models.FamilyFoodPrice;

import java.util.List;

public interface FamilyFoodPriceRepo extends CrudRepository<FamilyFoodPrice, Integer> {

    List<FamilyFoodPrice> getByOwnerAndMemberName(String owner, String memberName);

    List<FamilyFoodPrice> getFamilyFoodPricesByOwner(String owner);

    void deleteAllByOwner(String owner);
}
