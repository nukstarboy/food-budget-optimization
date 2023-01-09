package project.models;

public class Food {
    public String name;
    public String quantity;
    public double price;
    public double[] ingredients;

    public Food(String name, String quantity, double price, double[] ingredients) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.ingredients = ingredients;
    }
}
