package com.petr.create_employee.Employee;

import java.time.LocalDate;
import com.petr.create_employee.Employee.Employee.EmployeeStatus;
import com.petr.create_employee.Employee.validation.EndDate.DateRangeContainer;
import com.petr.create_employee.Employee.validation.EndDate.EndDateValid;
import com.petr.create_employee.Employee.validation.HoursPerWeek.HoursRangeContainer;

import jakarta.validation.constraints.Pattern;



@EndDateValid
//@HoursPerWeekValid
public class UpdateEmployeeDTO implements DateRangeContainer, HoursRangeContainer {
    @Pattern(regexp = ".*\\S.*", message = "First name cannot be empty")
    private String firstName;

    //@Pattern(regexp = ".*\\S.*", message = "Middle name cannot be empty")
    private String middleName;

    @Pattern(regexp = ".*\\S.*", message = "Last name cannot be empty")
    private String lastName;

    private EmployeeStatus employeeStatus;

    private String emailAddress;

    private String mobileNumber;

    private String address;

    private LocalDate startDate;

    private LocalDate endDate;

    //private Boolean onGoing;

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

    // public Boolean getOnGoing() {
    //     return onGoing;
    // }

    // public void setOnGoing(Boolean onGoing) {
    //     this.onGoing = onGoing;
    // }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public UpdateEmployeeDTO() {
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "UpdateEmployeeDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", employeeStatus=" + employeeStatus + ", emailAddress=" + emailAddress + ", mobileNumber="
                + mobileNumber + ", address=" + address + ", startDate=" + startDate + ", endDate=" + endDate
                + ", hoursPerWeek=" + hoursPerWeek + "]";
    }


}
