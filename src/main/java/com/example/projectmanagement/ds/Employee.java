package com.example.projectmanagement.ds;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long employeeId;

    private String firstName;

    private String lastName;
    private String email;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project theProject;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
