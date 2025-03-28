package com.petr.create_employee.Employee.validation.UniqueMobile;
import org.springframework.beans.factory.annotation.Autowired;
import com.petr.create_employee.Employee.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueMobileValidator implements ConstraintValidator<UniqueMobileValid, String>{

    @Autowired
    private EmployeeRepository repo;

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext context) {
        return !repo.existsByMobileNumber(mobile);
    }

}