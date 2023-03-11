package project.utils;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.springframework.stereotype.Service;
import project.models.Food;
import project.models.FoodPrice;
import project.models.Nutrient;
import project.models.NutrientsQuantity;

import java.util.ArrayList;
import java.util.List;

@Service
public class StiglerDiet {
    private final FoodInfo foodInfo;

    public StiglerDiet(FoodInfo foodInfo) {
        this.foodInfo = foodInfo;
    }

    public List<FoodPrice> foodPrices(List<Nutrient> nutrients, int planPeriod, String planOwner) {
        Loader.loadNativeLibraries();
        List<Food> food = foodInfo.addFoodInfo();

        // Create the linear solver with the GLOP backend.
        MPSolver solver = MPSolver.createSolver("GLOP");
        if (solver == null) {
            System.out.println("Could not create solver GLOP");
            return null;
        }

        double infinity = Double.POSITIVE_INFINITY;
        List<MPVariable> foods = new ArrayList<>();
        for (Food food1 : food) {
            foods.add(solver.makeNumVar(0.0, infinity, food1.name));
        }
        System.out.println("Number of variables = " + solver.numVariables());

        MPConstraint[] constraints = new MPConstraint[nutrients.size()];
        for (int i = 0; i < nutrients.size(); ++i) {
            constraints[i] = solver.makeConstraint(nutrients.get(i).quantity, infinity, nutrients.get(i).name);
            for (int j = 0; j < food.size(); ++j) {
                constraints[i].setCoefficient(foods.get(j), (food.get(j).ingredients)[i]);
            }
        }
        System.out.println("Number of constraints = " + solver.numConstraints());

        MPObjective objective = solver.objective();
        for (int i = 0; i < food.size(); ++i) {
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
                return null;
            }
        }

        List<FoodPrice> foodPrices = new ArrayList<>();
//        System.out.println("\nAnnual Foods:");
        for (int i = 0; i < foods.size(); ++i) {
            if (foods.get(i).solutionValue() > 0.0) {
                foodPrices.add(new FoodPrice(food.get(i).name, planPeriod * foods.get(i).solutionValue(), setPlanPeriod(planPeriod), planOwner));
                System.out.println(food.get(i).name + ": $" + planPeriod * foods.get(i).solutionValue());
            }
        }

        return foodPrices;
    }

    public String setPlanPeriod(int planPeriod) {
        if (planPeriod == 1) {
            return "Daily";
        } else if (planPeriod == 7) {
            return "Weekly";
        }
        return "Yearly";
    }

    public List<NutrientsQuantity> nutrientsQuantities(List<Nutrient> nutrients, int planPeriod, String planOwner) {
        Loader.loadNativeLibraries();
        List<Food> food = foodInfo.addFoodInfo();

        // Create the linear solver with the GLOP backend.
        MPSolver solver = MPSolver.createSolver("GLOP");
        if (solver == null) {
            System.out.println("Could not create solver GLOP");
            return null;
        }

        double infinity = Double.POSITIVE_INFINITY;
        List<MPVariable> foods = new ArrayList<>();
        for (Food food1 : food) {
            foods.add(solver.makeNumVar(0.0, infinity, food1.name));
        }
        System.out.println("Number of variables = " + solver.numVariables());

        MPConstraint[] constraints = new MPConstraint[nutrients.size()];
        for (int i = 0; i < nutrients.size(); ++i) {
            constraints[i] = solver.makeConstraint(nutrients.get(i).quantity, infinity, nutrients.get(i).name);
            for (int j = 0; j < food.size(); ++j) {
                constraints[i].setCoefficient(foods.get(j), (food.get(j).ingredients)[i]);
            }
        }
        System.out.println("Number of constraints = " + solver.numConstraints());

        MPObjective objective = solver.objective();
        for (int i = 0; i < food.size(); ++i) {
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
                return null;
            }
        }

        double[] nutrientsResult = new double[nutrients.size()];
        System.out.println("\nAnnual Foods:");
        for (int i = 0; i < foods.size(); ++i) {
            if (foods.get(i).solutionValue() > 0.0) {
                System.out.println(food.get(i).name + ": $" + planPeriod * foods.get(i).solutionValue());
                for (int j = 0; j < nutrients.size(); ++j) {
                    nutrientsResult[j] += (food.get(i).ingredients)[j] * foods.get(i).solutionValue();
                }
            }
        }

        List<NutrientsQuantity> nutrientsQuantities = new ArrayList<>();
        System.out.println("\nNutrients per day:");
        for (int i = 0; i < nutrients.size(); ++i) {
            NutrientsQuantity nutrientsQuantity = new NutrientsQuantity(nutrients.get(i).name, nutrientsResult[i], nutrients.get(i).quantity, planOwner);
            nutrientsQuantities.add(nutrientsQuantity);
            System.out.println(
                    nutrients.get(i).name + ": " + nutrientsResult[i] + " (min " + nutrients.get(i).quantity + ")");
        }

        return nutrientsQuantities;
    }

    public double optimalPrice(List<Nutrient> nutrients, int planPeriod) {
        Loader.loadNativeLibraries();
        List<Food> food = foodInfo.addFoodInfo();

        // Create the linear solver with the GLOP backend.
        MPSolver solver = MPSolver.createSolver("GLOP");
        if (solver == null) {
            System.out.println("Could not create solver GLOP");
            return 0;
        }

        double infinity = Double.POSITIVE_INFINITY;
        List<MPVariable> foods = new ArrayList<>();
        for (Food food1 : food) {
            foods.add(solver.makeNumVar(0.0, infinity, food1.name));
        }
        System.out.println("Number of variables = " + solver.numVariables());

        MPConstraint[] constraints = new MPConstraint[nutrients.size()];
        for (int i = 0; i < nutrients.size(); ++i) {
            constraints[i] = solver.makeConstraint(nutrients.get(i).quantity, infinity, nutrients.get(i).name);
            for (int j = 0; j < food.size(); ++j) {
                constraints[i].setCoefficient(foods.get(j), (food.get(j).ingredients)[i]);
            }
        }
        System.out.println("Number of constraints = " + solver.numConstraints());

        MPObjective objective = solver.objective();
        for (int i = 0; i < food.size(); ++i) {
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
                return 0;
            }
        }

        double[] nutrientsResult = new double[nutrients.size()];
        for (int i = 0; i < foods.size(); ++i) {
            if (foods.get(i).solutionValue() > 0.0) {
                System.out.println(food.get(i).name + ": $" + planPeriod * foods.get(i).solutionValue());
                for (int j = 0; j < nutrients.size(); ++j) {
                    nutrientsResult[j] += (food.get(i).ingredients)[j] * foods.get(i).solutionValue();
                }
            }
        }

        System.out.println("\nNutrients per day:");
        for (int i = 0; i < nutrients.size(); ++i) {
            System.out.println(
                    nutrients.get(i).name + ": " + nutrientsResult[i] + " (min " + nutrients.get(i).quantity + ")");
        }

        return planPeriod * objective.value();
    }

    public double problemSolvedTime(List<Nutrient> nutrients) {
        System.out.println("\nAdvanced usage:");
        MPSolver solver = problemSolved(nutrients);
        System.out.println("Problem solved in " + solver.wallTime() + " milliseconds");
        return solver.wallTime();
    }

    public long problemSolvedIterations(List<Nutrient> nutrients) {
        System.out.println("\nAdvanced usage:");
        MPSolver solver = problemSolved(nutrients);
        System.out.println("Problem solved in " + solver.iterations() + " iterations");
        return solver.iterations();
    }

    private MPSolver problemSolved(List<Nutrient> nutrients) {
        Loader.loadNativeLibraries();
        List<Food> food = foodInfo.addFoodInfo();

        // Create the linear solver with the GLOP backend.
        MPSolver solver = MPSolver.createSolver("GLOP");
        if (solver == null) {
            System.out.println("Could not create solver GLOP");
            return null;
        }

        double infinity = Double.POSITIVE_INFINITY;
        List<MPVariable> foods = new ArrayList<>();
        for (Food food1 : food) {
            foods.add(solver.makeNumVar(0.0, infinity, food1.name));
        }
        System.out.println("Number of variables = " + solver.numVariables());

        MPConstraint[] constraints = new MPConstraint[nutrients.size()];
        for (int i = 0; i < nutrients.size(); ++i) {
            constraints[i] = solver.makeConstraint(nutrients.get(i).quantity, infinity, nutrients.get(i).name);
            for (int j = 0; j < food.size(); ++j) {
                constraints[i].setCoefficient(foods.get(j), (food.get(j).ingredients)[i]);
            }
        }
        System.out.println("Number of constraints = " + solver.numConstraints());

        MPObjective objective = solver.objective();
        for (int i = 0; i < food.size(); ++i) {
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
                return null;
            }
        }

        double[] nutrientsResult = new double[nutrients.size()];
        System.out.println("\nAnnual Foods:");
        for (int i = 0; i < foods.size(); ++i) {
            if (foods.get(i).solutionValue() > 0.0) {
                System.out.println(food.get(i).name + ": $" + 365 * foods.get(i).solutionValue());
                for (int j = 0; j < nutrients.size(); ++j) {
                    nutrientsResult[j] += (food.get(i).ingredients)[j] * foods.get(i).solutionValue();
                }
            }
        }

        System.out.println("\nNutrients per day:");
        for (int i = 0; i < nutrients.size(); ++i) {
            System.out.println(
                    nutrients.get(i).name + ": " + nutrientsResult[i] + " (min " + nutrients.get(i).quantity + ")");
        }

        System.out.println("\nOptimal annual price: $" + 365 * objective.value());

        return solver;
    }
}
