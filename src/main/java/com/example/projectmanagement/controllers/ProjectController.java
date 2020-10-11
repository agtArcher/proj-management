package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.ProjectDao;
import com.example.projectmanagement.ds.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectDao projectDao;

    @GetMapping("/")
    public String displayProjectList(Model model) {
        model.addAttribute("projects", projectDao.findAll());

        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(@ModelAttribute("project") Project project) {
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute("project") Project project) {
        projectDao.save(project);

        return "redirect:/";
    }


}
