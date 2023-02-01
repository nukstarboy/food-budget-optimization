package project.models;

import javax.persistence.*;

@Entity
@Table(name = "task_solve_details")
public class TaskSolveDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @Column(name = "optimalAnnualPrice")
    public double optimalAnnualPrice;
    @Column(name = "problemSolvedTime")
    public double problemSolvedTime;
    @Column(name = "problemSolvedIterations")
    public long problemSolvedIterations;
    @Column(name = "owner")
    public String owner;

    public TaskSolveDetails() {
    }

    public TaskSolveDetails(double optimalAnnualPrice, double problemSolvedTime, long problemSolvedIterations, String owner) {
        this.optimalAnnualPrice = optimalAnnualPrice;
        this.problemSolvedTime = problemSolvedTime;
        this.problemSolvedIterations = problemSolvedIterations;
        this.owner = owner;
    }
}
