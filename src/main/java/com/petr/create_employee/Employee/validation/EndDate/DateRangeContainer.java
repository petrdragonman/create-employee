package com.petr.create_employee.Employee.validation.EndDate;

import java.time.LocalDate;

public interface DateRangeContainer {
    LocalDate getStartDate();
    LocalDate getEndDate();
}
