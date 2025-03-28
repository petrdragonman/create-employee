package com.petr.create_employee.Employee.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndDateValidator.class)
public @interface EndDateValid {
    String message() default "Invalid end date, must be after start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
