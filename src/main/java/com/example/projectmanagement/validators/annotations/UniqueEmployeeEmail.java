package com.example.projectmanagement.validators.annotations;

import com.example.projectmanagement.validators.UniqueEmployeeEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmployeeEmailValidator.class)
public @interface UniqueEmployeeEmail {

    String message() default "Unique Constraint violated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
