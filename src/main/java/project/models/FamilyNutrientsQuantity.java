package project.models;

import javax.persistence.*;

@Entity
@Table(name = "family_nutrients_quantity")
public class FamilyNutrientsQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @Column(name = "member_name")
    public String memberName;
    @Column(name = "nutrient_name")
    public String nutrientName;
    @Column(name = "normal_quantity_per_day")
    public double normalQuantityPerDay;
    @Column(name = "minimum_quantity_per_day")
    public double minimumQuantityPerDay;
    @Column(name = "owner")
    public String owner;

    public FamilyNutrientsQuantity() {
    }

    public FamilyNutrientsQuantity(String memberName, String nutrientName, double normalQuantityPerDay, double minimumQuantityPerDay, String owner) {
        this.memberName = memberName;
        this.nutrientName = nutrientName;
        this.normalQuantityPerDay = normalQuantityPerDay;
        this.minimumQuantityPerDay = minimumQuantityPerDay;
        this.owner = owner;
    }
}
