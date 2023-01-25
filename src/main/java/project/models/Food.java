package project.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Food {
    @Id
    public String id;
    public String name;
    public String quantity;
    public double price;
    public double[] ingredients;

    public Food() {
    }

    public Food(String name, String quantity, double price, double[] ingredients) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.ingredients = ingredients;
    }
}
