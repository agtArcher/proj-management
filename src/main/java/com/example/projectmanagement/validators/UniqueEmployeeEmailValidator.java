package com.example.projectmanagement.validators;

import com.example.projectmanagement.dao.EmployeeDao;
import com.example.projectmanagement.validators.annotations.UniqueEmployeeEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueEmployeeEmailValidator implements ConstraintValidator<UniqueEmployeeEmail, String> {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Entered validation method..");
        return (!employeeDao.findByEmail(s).isPresent());
    }
}
