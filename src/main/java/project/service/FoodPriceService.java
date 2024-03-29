package project.service;

import org.springframework.stereotype.Service;
import project.models.FamilyFoodPrice;
import project.models.FoodPrice;
import project.repo.FamilyFoodPriceRepo;
import project.repo.FoodPriceRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FoodPriceService {
    private final FoodPriceRepo foodPriceRepo;
    private final FamilyFoodPriceRepo familyFoodPriceRepo;

    public FoodPriceService(FoodPriceRepo foodPriceRepo,
                            FamilyFoodPriceRepo familyFoodPriceRepo) {
        this.foodPriceRepo = foodPriceRepo;
        this.familyFoodPriceRepo = familyFoodPriceRepo;
    }

    public List<FoodPrice> getAllFoodPricesByOwner(String owner) {
        return this.foodPriceRepo.getFoodPricesByOwner(owner);
    }

    public void saveAll(List<FoodPrice> foodPrices) {
        this.foodPriceRepo.saveAll(foodPrices);
    }

    public void saveAllFamilyFoodPrices(List<FamilyFoodPrice> familyFoodPrices) {
        this.familyFoodPriceRepo.saveAll(familyFoodPrices);
    }

    public List<FamilyFoodPrice> getByMemberNames(String owner, String memberName) {
        String[] members = memberName.split(", ");
        List<FamilyFoodPrice> combineFood = new ArrayList<>();
        Arrays.stream(members).forEach(member -> {
            List<FamilyFoodPrice> foodByMember = this.familyFoodPriceRepo.getByOwnerAndMemberName(owner, member);
            combineFood.addAll(foodByMember);
        });
        return combineFood;
    }

    public List<FamilyFoodPrice> getFamilyFoodPricesByOwner(String owner) {
        return this.familyFoodPriceRepo.getFamilyFoodPricesByOwner(owner);
    }

    @Transactional
    public void deleteAll(String owner) {
        this.foodPriceRepo.deleteAllByOwner(owner);
    }

    @Transactional
    public void deleteFamilyFoodPrices(String owner) {
        this.familyFoodPriceRepo.deleteAllByOwner(owner);
    }
}
