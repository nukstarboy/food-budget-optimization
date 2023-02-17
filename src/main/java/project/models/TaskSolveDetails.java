package project.models;

import javax.persistence.*;

@Entity
@Table(name = "task_solve_details")
public class TaskSolveDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @Column(name = "optimalPrice")
    public double optimalPrice;
    @Column(name = "problemSolvedTime")
    public double problemSolvedTime;
    @Column(name = "problemSolvedIterations")
    public long problemSolvedIterations;
    @Column(name = "owner")
    public String owner;
    @Column(name = "planPeriod")
    public String planPeriod;

    public TaskSolveDetails() {
    }

    public TaskSolveDetails(double optimalPrice, double problemSolvedTime, long problemSolvedIterations, String owner, String planPeriod) {
        this.optimalPrice = optimalPrice;
        this.problemSolvedTime = problemSolvedTime;
        this.problemSolvedIterations = problemSolvedIterations;
        this.owner = owner;
        this.planPeriod = planPeriod;
    }
}
