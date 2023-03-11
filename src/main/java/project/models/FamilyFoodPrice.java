package project.models;

import javax.persistence.*;

@Entity
@Table(name = "family_food_price")
public class FamilyFoodPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @Column(name = "memberName")
    public String memberName;
    @Column(name = "food")
    public String food;
    @Column(name = "optimalPrice")
    public double optimalPrice;
    @Column(name = "planPeriod")
    public String planPeriod;
    @Column(name = "owner")
    public String owner;

    public FamilyFoodPrice() {
    }

    public FamilyFoodPrice(String memberName, String food, double optimalPrice, String planPeriod, String owner) {
        this.memberName = memberName;
        this.food = food;
        this.optimalPrice = optimalPrice;
        this.planPeriod = planPeriod;
        this.owner = owner;
    }
}
