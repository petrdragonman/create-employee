package com.petr.create_employee.Employee;

import java.time.LocalDate;

import com.petr.create_employee.Employee.Employee.EmployeeStatus;

import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.Pattern;

public class UpdateEmployeeDTO {
    @Pattern(regexp = ".*\\S.*", message = "First name cannot be empty")
    private String firstName;

    @Pattern(regexp = ".*\\S.*", message = "First name cannot be empty")
    private String middleName;

    @Pattern(regexp = ".*\\S.*", message = "Last name cannot be empty")
    private String lastName;

    private EmployeeStatus employeeStatus;

    private String emailAddress;

    private String mobileNumber;

    private LocalDate startDate;

    private Boolean onGoing;

    @Min(0)
    private Integer hoursPerWeek;

    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Boolean getOnGoing() {
        return onGoing;
    }

    public void setOnGoing(Boolean onGoing) {
        this.onGoing = onGoing;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public UpdateEmployeeDTO() {
    }

    @Override
    public String toString() {
        return "UpdateEmployeeDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", employeeStatus=" + employeeStatus + "]";
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
