package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dto.ChartData;
import com.example.projectmanagement.services.EmployeeService;
import com.example.projectmanagement.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Value("${version}")
    private String ver;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @GetMapping({"/", "/home"})
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", ver);

        //querying the database for projects and employees and add result from query to model
        model.addAttribute("projects", projectService.findAll());
        Iterable<ChartData> projectData = projectService.getProjectStatus();

        //convert projectData into json structure for use in javascript

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        //[["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", jsonString);

        model.addAttribute("employeeListProjectsCnt", employeeService.employeeProjects());

        return "main/home";
    }

}
