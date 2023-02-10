package project;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import project.models.Nutrient;

import java.util.ArrayList;
import java.util.List;

/**
 * Stigler diet example.
 */
public final class Stigler {
    public static void main(String[] args) {
        Loader.loadNativeLibraries();

        // Nutrient minimums.
        List<Nutrient> nutrients = new ArrayList<>();
        double calculateCalories = getCalories(26, "male", 70, 197, "3");
        double calculateFat = getFat(calculateCalories, "soft");
        double calculateCholesterol = 200;
        double calculateSodium = getSodium(26, "male");
        double calculateCarbs = (calculateCalories * 0.55) * 0.25;
        double calculateFiber = getFiber(26, "male");
        double calculateSugar = getSugar("male");
        double calculateProtein = (calculateCalories * 0.23) / 10;
        nutrients.add(new Nutrient("Calories (cal)", calculateCalories));
        nutrients.add(new Nutrient("Fat (g)", calculateFat));
        nutrients.add(new Nutrient("Cholesterol (mg)", calculateCholesterol));
        nutrients.add(new Nutrient("Sodium (mg)", calculateSodium));
        nutrients.add(new Nutrient("Carbs (g)", calculateCarbs));
        nutrients.add(new Nutrient("Fiber (g)", calculateFiber));
        nutrients.add(new Nutrient("Sugar (g)", calculateSugar));
        nutrients.add(new Nutrient("Protein (g)", calculateProtein));

        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"Baked Chicken", new double[]{75, 3.6, 48.5, 38, 0.2, 0, 0, 10}});
        data.add(new Object[]{"Baked Fish", new double[]{26, 0.2, 13.5, 19.4, 0, 0, 0, 5.6}});
        data.add(new Object[]{"BBQ Chicken", new double[]{59.125, 2.25, 30.375, 98.25, 3, 0.125, 2.375, 6.25}});
        data.add(new Object[]{"BBQ Shredded Chicken", new double[]{29.33, 1, 11, 57.33, 1.5, 0, 1.16, 3.67}});
        data.add(new Object[]{"BBQ Tofu", new double[]{20.25, 0.75, 0, 38.75, 1.75, 0.25, 1.25, 1.75}});
        data.add(new Object[]{"Bean Burrito", new double[]{74, 1, 0, 38.75, 63, 4, 0.33, 4.33}});
        data.add(new Object[]{"Beef No Flesh Patty", new double[]{41.33, 1.33, 1.33, 132.67, 3.33, 1, 0.33, 3.67}});
        data.add(new Object[]{"Beef Taco", new double[]{85.67, 5, 27.67, 38, 1.33, 0.33, 0.67, 8.33}});
        data.add(new Object[]{"Beef Taco Salad", new double[]{129.43, 5.71, 20.29, 81.71, 11.71, 2.57, 1.14, 8.29}});
        data.add(new Object[]{"Braised Chicken", new double[]{33.8, 1.6, 12.2, 10, 0, 0, 0, 4.4}});
        data.add(new Object[]{"Breaded Fish", new double[]{105, 6.33, 12.67, 152, 8.33, 0.33, 0.33, 4}});
        data.add(new Object[]{"Cheese Pizza", new double[]{53.167, 2, 4.167, 110.33, 6.5, 0.67, 0.67, 2.67}});
        data.add(new Object[]{"Chef Salad", new double[]{67.25, 3.25, 58.75, 84.5, 2.5, 1, 1, 7.25}});
        data.add(new Object[]{"Egg Patty", new double[]{34, 2.5, 82, 27.5, 0, 0, 0, 3}});
        data.add(new Object[]{"Egg Salad", new double[]{65, 4.5, 109.5, 109.5, 2, 0, 1.5, 4}});
        data.add(new Object[]{"Fried Rice", new double[]{116.5, 2.5, 13, 9.5, 20.5, 0.5, 0.5, 2.5}});
        data.add(new Object[]{"Hamburger", new double[]{33.167, 2.167, 11.5, 8.67, 0, 0, 0, 3.33}});
        data.add(new Object[]{"Hummus", new double[]{84.667, 2, 0, 258.667, 12.667, 3.33, 2, 4}});
        data.add(new Object[]{"Lasagna", new double[]{128, 5.25, 34.5, 268.75, 10.25, 1, 3, 10}});
        data.add(new Object[]{"Meatballs", new double[]{77.667, 4.33, 25.33, 32.667, 1.33, 0.33, 0.33, 7}});
        data.add(new Object[]{"Pork Roast", new double[]{41.8, 3, 13.4, 10.4, 0, 0, 0, 3.6}});
        data.add(new Object[]{"Pork Roast", new double[]{41.8, 3, 13.4, 10.4, 0, 0, 0, 3.6}});
        data.add(new Object[]{"Tuna Salad", new double[]{50.667, 1, 10, 70, 2, 0.33, 1.667, 8}});

        data.add(new Object[]{"Baked Potato", new double[]{171, 0, 0, 18, 39, 4, 2, 5}});
        data.add(new Object[]{"Baked Sweet Potato", new double[]{124, 0, 0, 50, 29, 5, 9, 3}});
        data.add(new Object[]{"Baked Tater Tots", new double[]{82.25, 3.75, 0, 198.25, 11.75, 0.75, 0, 1}});
        data.add(new Object[]{"Egg Noodles", new double[]{39.4, 0.6, 8.2, 2.4, 7.2, 0.4, 0.2, 1.2}});
        data.add(new Object[]{"French Fries", new double[]{185.33, 9, 0, 6.33, 25, 2.667, 1, 2}});
        data.add(new Object[]{"Italian Pasta Salad", new double[]{69.75, 2.75, 0, 89, 9.5, 0.75, 0.5, 1.75}});
        data.add(new Object[]{"Pesto Pasta", new double[]{69.75, 2.75, 0, 89, 9.5, 0.75, 0.5, 1.75}});
        data.add(new Object[]{"Potato Chips", new double[]{75.5, 5, 0, 74.5, 7.5, 1, 0.5, 0.5}});
        data.add(new Object[]{"Spaghetti Pasta", new double[]{109, 1.667, 23, 5.667, 20, 1, 0.33, 3.667}});
        data.add(new Object[]{"Tortilla Chips", new double[]{67, 3, 0, 46.5, 9.5, 1, 0, 1}});

        data.add(new Object[]{"Bran Cereal", new double[]{90, 0.5, 0, 202.5, 23.5, 5, 5, 3}});
        data.add(new Object[]{"Breakfast Cake", new double[]{169.5, 9.5, 13.5, 51, 19, 0.5, 10, 2.5}});
        data.add(new Object[]{"French Toast", new double[]{101.5, 2.5, 52.5, 151, 14, 1.5, 3, 5.5}});
        data.add(new Object[]{"Pancakes", new double[]{102.5, 2, 22.5, 220.5, 17, 0.5, 2.5, 3.5}});

        data.add(new Object[]{"Apple", new double[]{200, 0, 0, 4, 50, 8, 40, 0}});
        data.add(new Object[]{"Apricot", new double[]{180, 0, 0, 2, 25, 4, 20, 2}});
        data.add(new Object[]{"Banana", new double[]{250, 0, 0, 2, 60, 8, 30, 2}});
        data.add(new Object[]{"Banana", new double[]{250, 0, 0, 2, 60, 8, 30, 2}});
        data.add(new Object[]{"Blueberries", new double[]{84, 0, 0, 1, 21, 4, 15, 1}});
        data.add(new Object[]{"Cherries", new double[]{97, 0, 0, 0, 25, 3, 20, 2}});
        data.add(new Object[]{"Grapes", new double[]{104, 0, 0, 3, 27, 1, 23, 1}});
        data.add(new Object[]{"Kiwi", new double[]{90, 0, 0, 4, 20, 4, 12, 2}});
        data.add(new Object[]{"Mango", new double[]{99, 1, 0, 2, 25, 3, 23, 1}});
        data.add(new Object[]{"Nectarine", new double[]{130, 0, 0, 0, 30, 4, 23, 2}});
        data.add(new Object[]{"Orange", new double[]{130, 0, 0, 0, 30, 4, 23, 2}});
        data.add(new Object[]{"Peach", new double[]{130, 0, 0, 0, 28, 4, 26, 2}});
        data.add(new Object[]{"Pear", new double[]{200, 0, 0, 4, 54, 12, 35, 2}});
        data.add(new Object[]{"Strawberries", new double[]{70, 0, 0, 2, 20, 5, 10, 2}});
        data.add(new Object[]{"Watermelon", new double[]{46, 0, 0, 2, 11, 1, 10, 2}});

        data.add(new Object[]{"Black Bean Soup", new double[]{156, 1, 1, 7, 27, 9, 2, 10}});
        data.add(new Object[]{"Cream of Potato Soup", new double[]{140, 1, 1, 50, 27, 3, 5, 5}});

        data.add(new Object[]{"Milk", new double[]{83.5, 0, 5, 103, 12, 0, 13, 9}});

        // Create the linear solver with the GLOP backend.
        MPSolver solver = MPSolver.createSolver("GLOP");
        if (solver == null) {
            System.out.println("Could not create solver GLOP");
            return;
        }

        double infinity = java.lang.Double.POSITIVE_INFINITY;
        List<MPVariable> foods = new ArrayList<>();
        for (int i = 0; i < data.size(); ++i) {
            foods.add(solver.makeNumVar(0.0, infinity, (String) data.get(i)[0]));
        }
        System.out.println("Number of variables = " + solver.numVariables());

        MPConstraint[] constraints = new MPConstraint[nutrients.size()];
        for (int i = 0; i < nutrients.size(); ++i) {
            constraints[i] = solver.makeConstraint(nutrients.get(i).quantity, infinity, nutrients.get(i).name);
            for (int j = 0; j < data.size(); ++j) {
                constraints[i].setCoefficient(foods.get(j), ((double[]) data.get(j)[1])[i]);
            }
        }
        System.out.println("Number of constraints = " + solver.numConstraints());

        MPObjective objective = solver.objective();
        for (int i = 0; i < data.size(); ++i) {
            objective.setCoefficient(foods.get(i), 1);
        }
        objective.setMinimization();

        final MPSolver.ResultStatus resultStatus = solver.solve();

        // Check that the problem has an optimal solution.
        if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
            System.err.println("The problem does not have an optimal solution!");
            if (resultStatus == MPSolver.ResultStatus.FEASIBLE) {
                System.err.println("A potentially suboptimal solution was found.");
            } else {
                System.err.println("The solver could not solve the problem.");
                return;
            }
        }

        // Display the amounts (in dollars) to purchase of each food.
        double[] nutrientsResult = new double[nutrients.size()];
        System.out.println("\nAnnual Foods:");
        for (int i = 0; i < foods.size(); ++i) {
            if (foods.get(i).solutionValue() > 0.0) {
                System.out.println((String) data.get(i)[0] + ": $" + 365 * foods.get(i).solutionValue());
                for (int j = 0; j < nutrients.size(); ++j) {
                    nutrientsResult[j] += ((double[]) data.get(i)[1])[j] * foods.get(i).solutionValue();
                }
            }
        }
        System.out.println("\nOptimal annual price: $" + 365 * objective.value());

        System.out.println("\nNutrients per day:");
        for (int i = 0; i < nutrients.size(); ++i) {
            System.out.println(
                    nutrients.get(i).name + ": " + nutrientsResult[i] + " (min " + nutrients.get(i).quantity + ")");
        }

        System.out.println("\nAdvanced usage:");
        System.out.println("Problem solved in " + solver.wallTime() + " milliseconds");
        System.out.println("Problem solved in " + solver.iterations() + " iterations");
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

    private static double getCalories(int age, String gender, int weight, int height, String activity) {
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

    private static double calculateMaleCalories(int weight, int height, int age) {
        return (66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age));
    }

    private static double calculateFemaleCalories(int weight, int height, int age) {
        return (655 + (9.563 * weight) + (1.850 * height) - (4.676 * age));
    }

}
