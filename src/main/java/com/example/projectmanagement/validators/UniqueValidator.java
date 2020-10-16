package com.example.projectmanagement.validators;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Entered validation method..");
        return (!employeeDao.findByEmail(s).isPresent());
    }
}
