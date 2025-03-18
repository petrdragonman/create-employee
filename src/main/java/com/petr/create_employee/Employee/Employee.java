package com.petr.create_employee.Employee;

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
        CONTRACT
    }

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = true)
    private String middleName = "";

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;


    

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

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public Employee() {
    }

    public Employee(String firstName, String middleName, String lastName, EmployeeStatus status) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.status = status;
    }

    

    // @Column(unique = true)
    // private String emailAddress;

    // @Column(unique = true)
    // private String mobileNumber;

    // @Column()
    // private String residentialAddress;

    // @Column()
    // private Date startDate;

    // @Column()
    // private Date finishDate;

    // @Column()
    // private Boolean onGoing;

    // @Column()
    // private Integer hoursPerWeek;



    // employee status -> permanent, contract
    // start date => day, month, year
    // finish date => day, month, year
    // onGoing => true, false
    // full-time, part-time
    

    
}
