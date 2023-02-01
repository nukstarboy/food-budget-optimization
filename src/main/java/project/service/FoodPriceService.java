package project.service;

import org.springframework.stereotype.Service;
import project.models.FoodPrice;
import project.repo.FoodPriceRepo;

import java.util.List;

@Service
public class FoodPriceService {
    private final FoodPriceRepo foodPriceRepo;

    public FoodPriceService(FoodPriceRepo foodPriceRepo) {
        this.foodPriceRepo = foodPriceRepo;
    }

    public List<FoodPrice> getAllFoodPricesByOwner(String owner) {
        return this.foodPriceRepo.getFoodPricesByOwner(owner);
    }

    public void saveAll(List<FoodPrice> foodPrices) {
        this.foodPriceRepo.saveAll(foodPrices);
    }
}
