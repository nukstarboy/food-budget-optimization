package project.models;

import javax.persistence.*;

@Entity
@Table(name = "admin_detail")
public class AdminDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private int adminID;

    @Column(name = "email_id", unique = true)
    public String emailId;

    @Column(name = "name")
    public String name;

    @Column(name = "password")
    public String password;

    @Column(name = "role")
    public String role;

    @Column(name = "dueOn")
    public String dueOn;

    public AdminDetail() {
    }

    public AdminDetail(int adminID, String emailId, String name, String password, String role, String dueOn) {
        super();
        this.adminID = adminID;
        this.emailId = emailId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.dueOn = dueOn;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDueOn() {
        return dueOn;
    }

    public void setDueOn(String dueOn) {
        this.dueOn = dueOn;
    }

}
