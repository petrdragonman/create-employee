package com.petr.create_employee.Employee;
import java.time.LocalDate;

import com.petr.create_employee.Employee.Employee.EmployeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEmployeeDTO {
    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotNull
    private EmployeeStatus status;

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String mobileNumber;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Boolean onGoing;

    @NotNull
    @Min(0)
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

    public EmployeeStatus getStatus() {
        return status;
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
                + ", status=" + status + ", emailAddress=" + emailAddress + ", mobileNumber=" + mobileNumber
                + ", startDate=" + startDate + ", onGoing=" + onGoing + ", hoursPerWeek=" + hoursPerWeek + "]";
    }
    
}
