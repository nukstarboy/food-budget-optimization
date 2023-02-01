package project.models;

import javax.persistence.*;

@Entity
@Table(name = "nutrients_quantity")
public class NutrientsQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @Column(name = "nutrient_name")
    public String nutrientName;
    @Column(name = "normal_quantity_per_day")
    public double normalQuantityPerDay;
    @Column(name = "minimum_quantity_per_day")
    public double minimumQuantityPerDay;
    @Column(name = "owner")
    public String owner;

    public NutrientsQuantity() {
    }

    public NutrientsQuantity(String nutrientName, double normalQuantityPerDay, double minimumQuantityPerDay, String owner) {
        this.nutrientName = nutrientName;
        this.normalQuantityPerDay = normalQuantityPerDay;
        this.minimumQuantityPerDay = minimumQuantityPerDay;
        this.owner = owner;
    }
}
