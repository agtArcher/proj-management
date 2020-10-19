package com.example.projectmanagement.validators;

import com.example.projectmanagement.dao.UserAccountDao;
import com.example.projectmanagement.validators.annotations.UniqueUserEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String>  {

    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (!userAccountDao.findFirstByEmail(s).isPresent());
    }
}
