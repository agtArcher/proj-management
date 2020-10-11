package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Long> {
}
