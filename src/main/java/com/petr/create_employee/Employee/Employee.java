package com.petr.create_employee.Employee;

import com.petr.create_employee.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee extends BaseEntity {
    @Column(nullable = false)
    private String fistName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    // @Column(nullable = false)
    // private String emailAddress;

    // @Column(nullable = false)
    // private String mobileNumber;

    // @Column(nullable = false)
    // private String residentialAddress;

    // @Column(nullable = false)
    // private String hoursPerWeek;

    // employee status -> permanent, contract
    // start date => day, month, year
    // finish date => day, month, year
    // onGoing => true, false
    // full-time, part-time
    
}
