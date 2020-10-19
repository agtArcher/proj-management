package com.example.projectmanagement.validators;

import com.example.projectmanagement.dao.UserAccountDao;
import com.example.projectmanagement.validators.annotations.UniqueUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (!userAccountDao.findFirstByUserName(s).isPresent());
    }
}
