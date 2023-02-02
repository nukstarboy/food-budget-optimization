package project.service;

import org.springframework.stereotype.Service;
import project.models.NutrientsQuantity;
import project.repo.NutrientsQuantityRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NutrientsQuantityService {
    private final NutrientsQuantityRepo nutrientsQuantityRepo;

    public NutrientsQuantityService(NutrientsQuantityRepo nutrientsQuantityRepo) {
        this.nutrientsQuantityRepo = nutrientsQuantityRepo;
    }

    public List<NutrientsQuantity> getAllNutrientsQuantityByOwner(String owner) {
        return this.nutrientsQuantityRepo.getAllByOwner(owner);
    }

    public void saveAll(List<NutrientsQuantity> nutrientsQuantities) {
        this.nutrientsQuantityRepo.saveAll(nutrientsQuantities);
    }

    @Transactional
    public void deleteAll(String owner) {
        this.nutrientsQuantityRepo.deleteAllByOwner(owner);
    }
}
