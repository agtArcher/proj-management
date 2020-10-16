package com.example.projectmanagement.controllers;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.ds.Employee;
import com.example.projectmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String saveEmployee(Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees/";
    }

    //problem with inserting new record instead updating it resolved adding setter for id in employee entity
    @GetMapping("/update")
    public String displayUpdateEmployeeForm(@RequestParam("id") long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);

        return "employees/new-employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long id) {
        employeeService.deleteById(id);

        return "redirect:/employees/";
    }
}
