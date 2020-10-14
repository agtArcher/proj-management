package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.ds.Employee;
import com.example.projectmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/new")
    public String displayEmployeeForm(@ModelAttribute("employee") Employee employee) {
        return "employees/new-employee";
    }

    @GetMapping("/")
    public String displayEmployeesList(Model model) {
        model.addAttribute("employees", employeeService.findAll());

        return "employees/list-employees";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees/";
    }

}
