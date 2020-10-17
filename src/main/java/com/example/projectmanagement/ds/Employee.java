package com.example.projectmanagement.ds;

import com.example.projectmanagement.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {

    @Id
    //Auto - инкрементом занимается hibernate, Identity - инкрементом занимается сама бд
    //такое решение помогает избежать проблем при добавлении начальных данных через sql файл
    //Sequence - инкрементирует бд, а hibernate просто берет сгенерированный id.
    //sequence позволяет использовать batch запросы, в отличии от identity
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @SequenceGenerator(name = "employee_generator", sequenceName = "employee_seq", allocationSize = 1)
    private long employeeId;

    @NotBlank(message = "*First name cannot be empty")
    @Size(min = 2, max = 50, message = "*size must be between 2 and 50 symbols")
    private String firstName;

    @NotBlank(message = "*Last name cannot be empty")
    @Size(min = 1, max = 50, message = "*size must be between 1 and 50 symbols")
    private String lastName;

    @NotBlank(message = "*Must be a valid email")
    @Email
    @UniqueValue
    private String email;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonIgnore //for avoid stack overflow error. Need to place in both relational entities.
    private List<Project> projects;

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }
}
