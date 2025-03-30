package com.petr.create_employee.Employee.validation.HoursPerWeek;
import com.petr.create_employee.Employee.Employee.EmployeeStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HoursPerWeekValidator implements ConstraintValidator<HoursPerWeekValid, Object> {
    private static final int FULL_TIME_HOURS = 40;
    private static final int MAX_PART_TIME_HOURS = 35;

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        if (!(dto instanceof HoursRangeContainer container)) {
            return true;
        }

        EmployeeStatus employeeStatus = container.getEmployeeStatus();
        Integer hours = container.getHoursPerWeek();

        if (employeeStatus == null || hours == null) {
            context.buildConstraintViolationWithTemplate("Employee status and hours must be specified")
                  .addPropertyNode("hoursPerWeek")
                  .addConstraintViolation();
            return false;
        }

        boolean isValid = switch (employeeStatus) {
            case PERMANENT_FULL_TIME -> hours == FULL_TIME_HOURS;
            case PERMANENT_PART_TIME -> hours > 0 && hours <= MAX_PART_TIME_HOURS;
            case CONTRACT, CASUAL -> hours > 0 && hours <= FULL_TIME_HOURS;
            default -> false;
        };

        if (!isValid) {
            String message = switch (employeeStatus) {
                case PERMANENT_FULL_TIME -> "Permanent full-time employees must work exactly 40 hours";
                case PERMANENT_PART_TIME -> "Permanent part-time employees must work 1-35 hours";
                case CONTRACT, CASUAL -> "Contract/Casual employees must work 1-40 hours";
                default -> "Invalid employee status";
            };
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                   .addPropertyNode("hoursPerWeek")
                   .addConstraintViolation();
        }

        return isValid;
    }
}
