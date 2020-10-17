package com.example.projectmanagement.controllers;

import com.example.projectmanagement.ds.Project;
import com.example.projectmanagement.services.EmployeeService;
import com.example.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return "projects/form-project";
    }

    @PostMapping("/save")
    public String saveProject(@Valid Project project, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("allEmployees", employeeService.findAll());
            return "projects/form-project";
        }
        projectService.save(project);
        return "redirect:/projects/";
    }

    @GetMapping("/update")
    public String displayProjectUpdateForm(@RequestParam("id") long id, Model model) {
        Project project = projectService.findProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("allEmployees", employeeService.findAll());

        return "projects/form-project";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("id") long id) {
        projectService.deleteById(id);

        return "redirect:/projects/";
    }

    @GetMapping("/timelines")
    public String timelinesList(Model model) {
        model.addAttribute("projects", projectService.projectDates());

        return "projects/timelines";
    }
}
