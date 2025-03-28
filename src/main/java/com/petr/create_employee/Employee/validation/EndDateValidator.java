package com.petr.create_employee.Employee.validation;
import java.time.LocalDate;

import com.petr.create_employee.Employee.CreateEmployeeDTO;
import com.petr.create_employee.Employee.Employee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndDateValidator implements ConstraintValidator<EndDateValid, Object> {

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        if (dto instanceof DateRangeContainer) {
            DateRangeContainer container = (DateRangeContainer) dto;
            LocalDate startDate = container.getStartDate();
            LocalDate endDate = container.getEndDate();

            // Skip validation if endDate is null (optional)
            if (endDate == null) {
                return true;
            }

            // Validate endDate > startDate
            if (startDate == null || !endDate.isAfter(startDate)) {
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                      .addPropertyNode("endDate")
                      .addConstraintViolation();
                return false;
            }
        }
        return true;
        

        
    }
}
