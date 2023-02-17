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
    @Column(name = "optimalPrice")
    public double optimalPrice;
    @Column(name = "planPeriod")
    public String planPeriod;
    @Column(name = "owner")
    public String owner;

    public FoodPrice() {
    }

    public FoodPrice(String food, double optimalPrice, String planPeriod, String owner) {
        this.food = food;
        this.optimalPrice = optimalPrice;
        this.planPeriod = planPeriod;
        this.owner = owner;
    }
}
