package com.petr.create_employee.Employee.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.petr.create_employee.Employee.EmployeeRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueMobileValidator implements ConstraintValidator<UniqueMobileValid, String>{

    @Autowired
    EmployeeRepository repo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // boolean existsByMobileNumber(String mobileNumber);
        return !repo.existsByMobileNumber(value);
    }

    
}