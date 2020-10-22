package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.Employee;
import com.example.projectmanagement.dto.EmployeeProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "api-employees", path = "api-employees")
public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long> {

    @Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id " +
            " GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    Iterable<EmployeeProject> employeeProjects();

    Optional<Employee> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT email FROM employee WHERE employee_id = ?")
    Optional<String> findEmailByEmployeeId(long id);
}
