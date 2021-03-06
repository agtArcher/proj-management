package com.example.projectmanagement.api.controllers;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/app-api/employees")
public class EmployeeApiController {

    @Autowired
    private EmployeeDao employeeDao;


    @GetMapping
    public Iterable<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") long employeeId) {
        return employeeDao.findById(employeeId).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee) {
        return employeeDao.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid Employee employee) {

        return employeeDao.save(employee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Employee patchEmployee) throws Exception {
        Employee employeeToUpdate = employeeDao.findById(id).orElseThrow(Exception::new);
        if (patchEmployee.getEmail() != null) {
            employeeToUpdate.setEmail(patchEmployee.getEmail());
        }
        if (patchEmployee.getFirstName() != null) {
            employeeToUpdate.setFirstName(patchEmployee.getFirstName());
        }
        if (patchEmployee.getLastName() != null) {
            employeeToUpdate.setLastName(patchEmployee.getLastName());
        }

        return employeeDao.save(employeeToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        try {
            employeeDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        return employeeDao.findAll(pageAndSize);
    }
}
