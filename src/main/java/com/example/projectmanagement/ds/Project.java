package com.example.projectmanagement.ds;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private long projectId;
    private String name;
    private String stage; //NOTSTARTED, COMPLETED, INPROGRESS

    private String description;

    @OneToMany(mappedBy = "theProject")
    private List<Employee> employees;


    public Project(String name, String stage, String description) {
        super();
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public Project() {
    }

    public long getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
