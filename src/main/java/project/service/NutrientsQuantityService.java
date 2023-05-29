package project.service;

import org.springframework.stereotype.Service;
import project.models.FamilyNutrientsQuantity;
import project.models.NutrientsQuantity;
import project.repo.FamilyNutrientsQuantityRepo;
import project.repo.NutrientsQuantityRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NutrientsQuantityService {
    private final NutrientsQuantityRepo nutrientsQuantityRepo;
    private final FamilyNutrientsQuantityRepo familyNutrientsQuantityRepo;

    public NutrientsQuantityService(NutrientsQuantityRepo nutrientsQuantityRepo,
                                    FamilyNutrientsQuantityRepo familyNutrientsQuantityRepo) {
        this.nutrientsQuantityRepo = nutrientsQuantityRepo;
        this.familyNutrientsQuantityRepo = familyNutrientsQuantityRepo;
    }

    public List<NutrientsQuantity> getAllNutrientsQuantityByOwner(String owner) {
        return this.nutrientsQuantityRepo.getAllByOwner(owner);
    }

    public List<FamilyNutrientsQuantity> getAllFamilyNutrientsQuantityByMembers(String members) {
        String[] memberNames = members.split(", ");
        List<FamilyNutrientsQuantity> combineNutrients = new ArrayList<>();
        Arrays.stream(memberNames).forEach(member -> {
            List<FamilyNutrientsQuantity> nutrientsByMember = this.familyNutrientsQuantityRepo.getAllByMemberName(member);
            combineNutrients.addAll(nutrientsByMember);
        });
        return combineNutrients;
    }

    public List<FamilyNutrientsQuantity> getFamilyNutrientsQuantityByOwner(String owner) {
        return this.familyNutrientsQuantityRepo.getFamilyNutrientsQuantitiesByOwner(owner);
    }

    public void saveAll(List<NutrientsQuantity> nutrientsQuantities) {
        this.nutrientsQuantityRepo.saveAll(nutrientsQuantities);
    }

    public void saveAllFamilyNutrients(List<FamilyNutrientsQuantity> familyNutrientsQuantities) {
        this.familyNutrientsQuantityRepo.saveAll(familyNutrientsQuantities);
    }

    @Transactional
    public void deleteAll(String owner) {
        this.nutrientsQuantityRepo.deleteAllByOwner(owner);
    }

    @Transactional
    public void deleteFamilyNutrientsQuantity(String owner) {
        this.familyNutrientsQuantityRepo.deleteAllByOwner(owner);
    }
}
