package com.petr.create_employee.Employee;
import java.time.LocalDate;
import com.petr.create_employee.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

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
    private EmployeeStatus status;

    @Column()
    private LocalDate startDate;

    @Column()
    private Boolean onGoing;

    @Column()
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

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
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
            EmployeeStatus status, LocalDate startDate, Boolean onGoing, Integer hoursPerWeek) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.status = status;
        this.startDate = startDate;
        this.onGoing = onGoing;
        this.hoursPerWeek = hoursPerWeek;
    }

    // @Column()
    // private String residentialAddress;

    // @Column()
    // private Date startDate;

    // @Column()
    // private Date finishDate;

    // employee status -> permanent, contract
    // start date => day, month, year
    // finish date => day, month, year
    // onGoing => true, false
    // full-time, part-time
    

    
}
