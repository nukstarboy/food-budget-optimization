package project.models;

import javax.persistence.*;

@Entity
@Table(name = "food_nutrients")
public class FoodNutrients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String foodName;

    private int calories;

    private int protein;

    private int calcium;

    private int iron;

    private int vitaminA;

    private int thiamine;

    private int riboflavin;

    private int niacin;

    private int ascorbicAcid;

    private String owner;

    public FoodNutrients() {
    }

    public FoodNutrients(String foodName, int calories, int protein, int calcium, int iron, int vitaminA, int thiamine, int riboflavin, int niacin, int ascorbicAcid, String owner) {
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCalcium() {
        return calcium;
    }

    public void setCalcium(int calcium) {
        this.calcium = calcium;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(int vitaminA) {
        this.vitaminA = vitaminA;
    }

    public int getThiamine() {
        return thiamine;
    }

    public void setThiamine(int thiamine) {
        this.thiamine = thiamine;
    }

    public int getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(int riboflavin) {
        this.riboflavin = riboflavin;
    }

    public int getNiacin() {
        return niacin;
    }

    public void setNiacin(int niacin) {
        this.niacin = niacin;
    }

    public int getAscorbicAcid() {
        return ascorbicAcid;
    }

    public void setAscorbicAcid(int ascorbicAcid) {
        this.ascorbicAcid = ascorbicAcid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
