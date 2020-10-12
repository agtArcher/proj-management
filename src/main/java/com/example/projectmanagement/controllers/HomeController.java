package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.dao.ProjectDao;
import com.example.projectmanagement.dto.ChartData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ProjectDao projectDao;

    @GetMapping({"/", "/home"})
    public String displayHome(Model model) throws JsonProcessingException {
        //querying the database for projects and employees and add result from query to model
        model.addAttribute("projects", projectDao.findAll());
        Iterable<ChartData> projectData = projectDao.getProjectStatus();
        //Map<String, Object> map = new HashMap<>();

        //convert projectData into json structure for use in javascript

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        //[["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", jsonString);

        model.addAttribute("employeeListProjectsCnt", employeeDao.employeeProjects());

        return "main/home";
    }

}
