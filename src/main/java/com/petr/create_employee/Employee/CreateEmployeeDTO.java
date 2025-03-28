package com.petr.create_employee.Employee;
import java.time.LocalDate;

import com.petr.create_employee.Employee.Employee.EmployeeStatus;
import com.petr.create_employee.Employee.validation.DateRangeContainer;
import com.petr.create_employee.Employee.validation.EndDateValid;
import com.petr.create_employee.Employee.validation.UniqueMobileValid;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@EndDateValid
public class CreateEmployeeDTO implements DateRangeContainer {
    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotNull
    private EmployeeStatus employeeStatus;

    @NotBlank
    private String emailAddress;

    @NotBlank
    @UniqueMobileValid(message = "Mobile number must be unique.")
    private String mobileNumber;

    @NotBlank
    private String address;

    @NotNull
    private LocalDate startDate;


    private LocalDate endDate;

    @NotNull
    private Boolean onGoing;

    @NotNull
    @Min(value = 1, message = "Hours per week must be at least 1")
    @Max(value = 40, message = "Hours per week can not be more than 40")
    private Integer hoursPerWeek;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Boolean getOnGoing() {
        return onGoing;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    

    @Override
    public String toString() {
        return "CreateEmployeeDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", employeeStatus=" + employeeStatus + ", emailAddress=" + emailAddress + ", mobileNumber="
                + mobileNumber + ", address=" + address + ", startDate=" + startDate + ", endDate=" + endDate
                + ", onGoing=" + onGoing + ", hoursPerWeek=" + hoursPerWeek + "]";
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
}
