package com.petr.create_employee.Employee.validation.HoursPerWeek;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HoursPerWeekValidator.class)
public @interface HoursPerWeekValid {
    String message() default "Invalid Hours per week for Employee Status.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
