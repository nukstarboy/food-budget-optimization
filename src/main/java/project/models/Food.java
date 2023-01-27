package project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;
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
