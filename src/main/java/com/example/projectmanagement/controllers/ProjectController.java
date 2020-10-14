package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.dao.ProjectDao;
import com.example.projectmanagement.ds.Project;
import com.example.projectmanagement.services.EmployeeService;
import com.example.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String displayProjectList(Model model) {
        model.addAttribute("projects", projectService.findAll());

        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("allEmployees", employeeService.findAll());
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.save(project);
        return "redirect:/projects/";
    }
}
