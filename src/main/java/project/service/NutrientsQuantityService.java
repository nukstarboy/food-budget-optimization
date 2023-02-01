package project.service;

import org.springframework.stereotype.Service;
import project.models.NutrientsQuantity;
import project.repo.NutrientsQuantityRepo;

import java.util.List;

@Service
public class NutrientsQuantityService {
    private final NutrientsQuantityRepo nutrientsQuantityRepo;

    public NutrientsQuantityService(NutrientsQuantityRepo nutrientsQuantityRepo) {
        this.nutrientsQuantityRepo = nutrientsQuantityRepo;
    }

    public void saveAll(List<NutrientsQuantity> nutrientsQuantities) {
        this.nutrientsQuantityRepo.saveAll(nutrientsQuantities);
    }
}
