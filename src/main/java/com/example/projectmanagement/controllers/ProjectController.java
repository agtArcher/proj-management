package com.example.projectmanagement.controllers;

import com.example.projectmanagement.ds.Project;
import com.example.projectmanagement.dto.TimeChartData;
import com.example.projectmanagement.services.EmployeeService;
import com.example.projectmanagement.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String saveProject(@Valid Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
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
    public String displayProjectTimelines(Model model) throws JsonProcessingException {
        Iterable<TimeChartData> timeChartData = projectService.getTimeData();


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTimeLineString = objectMapper.writeValueAsString(timeChartData);

        model.addAttribute("projectTimeList", jsonTimeLineString);

        System.out.println("-------- project timelines --------");
        System.out.println(jsonTimeLineString);

        return "projects/timelines";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
