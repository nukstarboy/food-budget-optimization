package project.utils;

import org.springframework.stereotype.Service;
import project.models.Nutrient;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutrientCalculator {
    public List<Nutrient> addNutrients(int age, String gender, double weight, double height, String activity, String bodyType) throws Exception {
        List<Nutrient> nutrients = new ArrayList<>();
        double calculateCalories = getCalories(age, gender, weight, height, activity);
        double calculateFat = getFat(calculateCalories, bodyType);
        double calculateCholesterol = 200;
        double calculateSodium = getSodium(age, gender);
        double calculateCarbs = (calculateCalories * 0.55) * 0.25;
        double calculateFiber = getFiber(age, gender);
        double calculateSugar = getSugar(gender);
        double calculateProtein = (calculateCalories * 0.23) / 10;
        nutrients.add(new Nutrient("Calories (cal)", calculateCalories));
        nutrients.add(new Nutrient("Fat (g)", calculateFat));
        nutrients.add(new Nutrient("Cholesterol (mg)", calculateCholesterol));
        nutrients.add(new Nutrient("Sodium (mg)", calculateSodium));
        nutrients.add(new Nutrient("Carbs (g)", calculateCarbs));
        nutrients.add(new Nutrient("Fiber (g)", calculateFiber));
        nutrients.add(new Nutrient("Sugar (g)", calculateSugar));
        nutrients.add(new Nutrient("Protein (g)", calculateProtein));
        return nutrients;
    }

    private static double getFiber(int age, String gender) {
        if (age < 50){
            if (gender.equals("male")) {
                return 38;
            }
            return 25;
        } else {
            if (gender.equals("male")) {
                return 30;
            }
            return 21;
        }
    }

    private static double getSodium(int age, String gender) {
        if (age < 20) {
            return 2300;
        } else if (age > 20 && age < 50) {
            if (gender.equals("male")) {
                return 2000;
            } else {
                return 1850;
            }
        }
        if (gender.equals("male")) {
            return 1500;
        }
        return 1350;
    }

    private static double getSugar(String gender) {
        if (gender.equals("male")) {
            return 35;
        }
        return 25;
    }

    private static double getFat(double calculateCalories, String bodyType) {
        //TODO should check exact names
        if (bodyType.equals("lean")) {
            return (calculateCalories * 0.20) / 10;
        } else if (bodyType.equals("muscular")) {
            return (calculateCalories * 0.28) / 10;
        }

        return (calculateCalories * 0.35) / 10;
    }

    private static double getCalories(int age, String gender, double weight, double height, String activity) throws Exception {
        if (age <= 0 || weight <= 0 || height <= 0) {
            throw new Exception("Incorrect person information passed!");
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

    private static double calculateMaleCalories(double weight, double height, int age) {
        return (66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age));
    }

    private static double calculateFemaleCalories(double weight, double height, int age) {
        return (655 + (9.563 * weight) + (1.850 * height) - (4.676 * age));
    }
}
