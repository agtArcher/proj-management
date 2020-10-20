package com.example.projectmanagement.validators;

import com.example.projectmanagement.ds.RegisterData;
import com.example.projectmanagement.validators.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        RegisterData user = (RegisterData) obj;
        return user.getPassword().equals(user.getPassConfirm());
    }
}
