package project.utils;

import org.springframework.stereotype.Service;
import project.models.Nutrient;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutrientCalculator {
    public List<Nutrient> addNutrients(int age, String gender, double weight, double height, String activity) {
        List<Nutrient> nutrients = new ArrayList<>();
        double calculateCalories = getCalories(age, gender, weight, height, activity) / 1000;
        double calculateProtein = (calculateCalories * 0.23) / 10;
        double calculateIron = getIron(age, gender);
        double calculateVitaminB1 = getVitaminB1(age, gender);
        double calculateVitaminB2 = getVitaminB2(age, gender);
        double calculateVitaminC = getVitaminC(age, gender);
        double calculateNiacin = getNiacin(age, gender);
        nutrients.add(new Nutrient("Calories (kcal)", calculateCalories));
        nutrients.add(new Nutrient("Protein (g)", calculateProtein));
        nutrients.add(new Nutrient("Calcium (g)", 0.8));
        nutrients.add(new Nutrient("Iron (mg)", calculateIron));
        nutrients.add(new Nutrient("Vitamin A (kIU)", 5.0));
        nutrients.add(new Nutrient("Vitamin B1 (mg)", calculateVitaminB1));
        nutrients.add(new Nutrient("Vitamin B2 (mg)", calculateVitaminB2));
        nutrients.add(new Nutrient("Niacin (mg)", calculateNiacin));
        nutrients.add(new Nutrient("Vitamin C (mg)", calculateVitaminC));
        return nutrients;
    }

    private double getNiacin(int age, String gender) {
        if (gender.equals("male")) {
            if (age < 13) {
                return 10;
            } else if (age > 13 && age <= 18){
                return 14;
            } else {
                return 16;
            }
        } else {
            if (age < 13) {
                return 10;
            } else if (age > 13 && age <= 18){
                return 12;
            } else {
                return 14;
            }
        }
    }

    private double getVitaminB1(int age, String gender) {
        if (gender.equals("male")) {
            if (age < 13) {
                return 0.7;
            } else {
                return 1.2;
            }
        } else {
            if (age < 13) {
                return 0.7;
            } else {
                return 1.1;
            }
        }
    }

    private double getVitaminB2(int age, String gender) {
        if (gender.equals("male")) {
            if (age < 13) {
                return 0.7;
            } else {
                return 1.3;
            }
        } else {
            if (age < 13) {
                return 0.7;
            } else {
                return 1.1;
            }
        }
    }

    private double getVitaminC(int age, String gender) {
        if (gender.equals("male")) {
            if (age < 13) {
                return 35;
            } else if (age > 13 && age <= 18) {
                return 65;
            } else {
                return 85;
            }
        } else {
            if (age < 13) {
                return 25;
            } else if (age > 13 && age <= 18) {
                return 55;
            } else {
                return 75;
            }
        }
    }

    private double getIron(int age, String gender) {
        if (gender.equals("male")) {
            if (age > 50) {
                return 70;
            } else if (age < 50 && age >= 30) {
                return 35;
            } else if (age < 30 && age >= 16) {
                return 18;
            } else {
                return 8;
            }
        } else {
            if (age > 16) {
                return 18;
            } else {
                return 6;
            }
        }
    }

    private double getCalories(int age, String gender, double weight, double height, String activity) {
        if (age == 0 || weight == 0 || height == 0 || 80 < age || age < 15) {
            return 0;
        }

        if (gender.equals("male") && activity.equals("1")) {
            return  1.2 * calculateMaleCalories(weight, height, age);
        } else if (gender.equals("male") && activity.equals("2")) {
            return 1.375 * calculateMaleCalories(weight, height, age);
        } else if (gender.equals("male") && activity.equals("3")) {
            return 1.55 * calculateMaleCalories(weight, height, age);
        } else if (gender.equals("male") && activity.equals("4")) {
            return 1.725 * calculateMaleCalories(weight, height, age);
        } else if (gender.equals("male") && activity.equals("5")) {
            return 1.9 * calculateMaleCalories(weight, height, age);
        } else if (gender.equals("female") && activity.equals("1")) {
            return 1.2 * calculateFemaleCalories(weight, height, age);
        } else if (gender.equals("female") && activity.equals("2")) {
            return 1.375 * calculateFemaleCalories(weight, height, age);
        } else if (gender.equals("female") && activity.equals("3")) {
            return 1.55 * calculateFemaleCalories(weight, height, age);
        } else if (gender.equals("female") && activity.equals("4")) {
            return 1.725 * calculateFemaleCalories(weight, height, age);
        } else {
            return 1.9 * calculateFemaleCalories(weight, height, age);
        }
    }

    private double calculateMaleCalories(double weight, double height, int age) {
        return (66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age));
    }

    private double calculateFemaleCalories(double weight, double height, int age) {
        return (655 + (9.563 * weight) + (1.850 * height) - (4.676 * age));
    }
}
