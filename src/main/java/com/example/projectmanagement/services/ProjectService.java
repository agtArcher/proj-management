package com.example.projectmanagement.services;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.dao.ProjectDao;
import com.example.projectmanagement.ds.Project;
import com.example.projectmanagement.dto.ChartData;
import com.example.projectmanagement.dto.ProjectDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;


    public List<Project> findAll() {
        return projectDao.findAll();
    }

    public Project save(Project project) {
        return projectDao.save(project);
    }

    public Iterable<ChartData> getProjectStatus() {
        return projectDao.getProjectStatus();
    }

    public Project findProjectById(long id) {
        return projectDao.findById(id).orElse(new Project());
    }

    public void deleteById(long id) {
        projectDao.deleteById(id);
    }

    public Iterable<ProjectDates> projectDates() {
        return projectDao.projectDates();
    }
}
