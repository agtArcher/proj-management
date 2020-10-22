package com.example.projectmanagement.services;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.ds.Employee;
import com.example.projectmanagement.dto.EmployeeProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public Iterable<Employee> findAll() {
        return employeeDao.findAll();
    }

    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    public Iterable<EmployeeProject> employeeProjects() {
        return employeeDao.employeeProjects();
    }

    public Employee findEmployeeById(long id) {
        return employeeDao.findById(id).orElse(new Employee());
    }

    public void deleteById(long id) {
        employeeDao.deleteById(id);
    }

    public Optional<String> findEmployeesEmailById(long id) {
        return employeeDao.findEmailByEmployeeId(id);
    }
}
