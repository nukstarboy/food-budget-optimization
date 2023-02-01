package project.models;

import javax.persistence.*;

@Entity
@Table(name = "food_price")
public class FoodPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @Column(name = "food")
    public String food;
    @Column(name = "optimalAnnualPrice")
    public double optimalAnnualPrice;
    @Column(name = "owner")
    public String owner;

    public FoodPrice() {
    }

    public FoodPrice(String food, double optimalAnnualPrice, String owner) {
        this.food = food;
        this.optimalAnnualPrice = optimalAnnualPrice;
        this.owner = owner;
    }
}
