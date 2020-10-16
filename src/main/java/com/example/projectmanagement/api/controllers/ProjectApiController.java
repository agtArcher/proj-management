package com.example.projectmanagement.api.controllers;

import com.example.projectmanagement.dao.ProjectDao;
import com.example.projectmanagement.ds.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/app-api/projects")
public class ProjectApiController {
    @Autowired
    private ProjectDao projectDao;

    @GetMapping
    public Iterable<Project> getProjects() {
        return projectDao.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") long projectId) {
        return projectDao.findById(projectId).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project Project) {
        return projectDao.save(Project);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project Project) {

        return projectDao.save(Project);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") long id, @RequestBody Project patchProject) throws Exception {
        Project ProjectToUpdate = projectDao.findById(id).orElseThrow(Exception::new);
        if (patchProject.getName() != null) {
            ProjectToUpdate.setName(patchProject.getName());
        }
        if (patchProject.getStage() != null) {
            ProjectToUpdate.setStage(patchProject.getStage());
        }
        if (patchProject.getDescription() != null) {
            ProjectToUpdate.setDescription(patchProject.getDescription());
        }

        return projectDao.save(ProjectToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        try {
            projectDao.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }
}
