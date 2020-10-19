package com.example.projectmanagement.validators;

import com.example.projectmanagement.validators.annotations.NotBlankDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class NotBlankDateValidator implements ConstraintValidator<NotBlankDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return date != null;
    }
}
