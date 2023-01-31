package project.models;

import javax.persistence.*;

@Entity
@Table(name = "personal_questions")
public class PersonalQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @Column(name = "gender")
    public String gender;
    @Column(name = "weight")
    public double weight;
    @Column(name = "age")
    public int age;
    @Column(name = "height")
    public double height;
    @Column(name = "bodyType")
    public String bodyType;
    @Column(name = "activity")
    public String activity;
    @Column(name = "workout")
    public String workout;
    @Column(name = "dietary_restrictions")
    public String dietaryRestrictions;
    @Column(name = "plan_owner")
    public String planOwner;
}
