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
    public Double problemSolvedTime;
    @Column(name = "problemSolvedIterations")
    public Long problemSolvedIterations;
    @Column(name = "owner")
    public String owner;
    @Column(name = "planPeriod")
    public String planPeriod;
    @Column(name = "planType")
    public String planType;
    @Column(name = "memberName")
    public String memberName;

    public TaskSolveDetails() {
    }

    public TaskSolveDetails(double optimalPrice, Double problemSolvedTime, Long problemSolvedIterations, String owner, String planPeriod, String planType, String memberName) {
        this.optimalPrice = optimalPrice;
        this.problemSolvedTime = problemSolvedTime;
        this.problemSolvedIterations = problemSolvedIterations;
        this.owner = owner;
        this.planPeriod = planPeriod;
        this.planType = planType;
        this.memberName = memberName;
    }
}
