package project.models;

public class NutrientsQuantity {
    public String nutrientName;
    public double normalQuantityPerDay;
    public double minimumQuantityPerDay;

    public NutrientsQuantity(String nutrientName, double normalQuantityPerDay, double minimumQuantityPerDay) {
        this.nutrientName = nutrientName;
        this.normalQuantityPerDay = normalQuantityPerDay;
        this.minimumQuantityPerDay = minimumQuantityPerDay;
    }
}
