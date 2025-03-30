package com.petr.create_employee.Employee.validation.UniqueMobile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueMobileValidator.class)
public @interface UniqueMobileValid {
    String message() default "The mobile number you have entered already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
