package com.petr.create_employee.Employee;
import java.time.LocalDate;
import com.petr.create_employee.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="employees")
public class Employee extends BaseEntity {

    public enum EmployeeStatus {
        PERMANENT,
        CONTRACT,
        CASUAL,
    }

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = true)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String emailAddress;

    @Column(unique = true)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus employeeStatus;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private Boolean onGoing;

    @Column(nullable = false)
    @Min(value = 15, message = "Hours per week must be at least 15")
    @Max(value = 40, message = "Hours per week can not be more than 40")
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

    public Employee() {
    }

    public Employee(String firstName, String middleName, String lastName, String emailAddress, String mobileNumber,
            EmployeeStatus employeeStatus, LocalDate startDate, Boolean onGoing, Integer hoursPerWeek) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.employeeStatus = employeeStatus;
        this.startDate = startDate;
        this.onGoing = onGoing;
        this.hoursPerWeek = hoursPerWeek;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
