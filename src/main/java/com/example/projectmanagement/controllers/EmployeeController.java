package com.example.projectmanagement.controllers;

import com.example.projectmanagement.ds.Employee;
import com.example.projectmanagement.services.EmployeeService;
import com.example.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("allProjects", projectService.findAll());
        return "employees/form-employee";
    }

    @GetMapping("/")
    public String displayEmployeesList(Model model) {
        model.addAttribute("employees", employeeService.findAll());

        return "employees/list-employees";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        //findEmployeesEmailById search in db employee's email
        //in case when record from db equals to record from employee, method ignore validation from @UniqueEmployeeEmail
        Optional<String> employeeEmail = employeeService.findEmployeesEmailById(employee.getEmployeeId());
        if (bindingResult.hasErrors() && (!employeeEmail.isPresent() || !employeeEmail.get().equals(employee.getEmail()))) {
            model.addAttribute("allProjects", projectService.findAll());
            return "employees/form-employee";
        }

        employeeService.save(employee);

        return "redirect:/employees/";
    }

    //problem with inserting new record instead updating it resolved adding setter for id in employee entity
    @GetMapping("/update")
    public String displayUpdateEmployeeForm(@RequestParam("id") long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("allProjects", projectService.findAll());
        return "employees/form-employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long id) {
        employeeService.deleteById(id);

        return "redirect:/employees/";
    }
}
