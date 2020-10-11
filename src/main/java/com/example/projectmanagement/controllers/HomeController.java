package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ProjectDao projectDao;

    @GetMapping({"/", "/home"})
    public String displayHome(Model model) {
        //querying the database for projects and employees and add result from query to model
        model.addAttribute("projects", projectDao.findAll());
        model.addAttribute("employees", employeeDao.findAll());

        return "home";
    }

}
