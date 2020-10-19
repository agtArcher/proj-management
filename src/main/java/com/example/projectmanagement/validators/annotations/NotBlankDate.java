package com.example.projectmanagement.validators.annotations;

import com.example.projectmanagement.validators.NotBlankDateValidator;
import com.example.projectmanagement.validators.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBlankDateValidator.class)
public @interface NotBlankDate {

    String message() default "Unique Constraint violated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
