package project.models;

import javax.persistence.*;

@Entity
@Table(name = "food_nutrients")
public class FoodNutrients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String foodName;

    private double calories;

    private double protein;

    private double calcium;

    private double iron;

    private double vitaminA;

    private double thiamine;

    private double riboflavin;

    private double niacin;

    private double ascorbicAcid;

    private String owner;

    public FoodNutrients() {
    }

    public FoodNutrients(String foodName, double calories, double protein, double calcium, double iron, double vitaminA, double thiamine, double riboflavin, double niacin, double ascorbicAcid, String owner) {
        this.foodName = foodName;
        this.calories = calories;
        this.protein = protein;
        this.calcium = calcium;
        this.iron = iron;
        this.vitaminA = vitaminA;
        this.thiamine = thiamine;
        this.riboflavin = riboflavin;
        this.niacin = niacin;
        this.ascorbicAcid = ascorbicAcid;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public double getThiamine() {
        return thiamine;
    }

    public void setThiamine(double thiamine) {
        this.thiamine = thiamine;
    }

    public double getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(double riboflavin) {
        this.riboflavin = riboflavin;
    }

    public double getNiacin() {
        return niacin;
    }

    public void setNiacin(double niacin) {
        this.niacin = niacin;
    }

    public double getAscorbicAcid() {
        return ascorbicAcid;
    }

    public void setAscorbicAcid(double ascorbicAcid) {
        this.ascorbicAcid = ascorbicAcid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
