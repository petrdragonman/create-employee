package com.petr.create_employee.Employee;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeEndToEndTest {
    @LocalServerPort
    private int port;

    private ArrayList<Employee> employees = new ArrayList<>();

    @Autowired
    private EmployeeRepository repo;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        repo.deleteAll();
        employees.clear();
        // set up data for tests
        // make sure you are saving valid data

        // Employee 1: Permanent Employee
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setMiddleName("Michael");
        employee1.setLastName("Doe");
        employee1.setEmailAddress("john.doe@example.com");
        employee1.setMobileNumber("+1234567890");
        employee1.setStatus(Employee.EmployeeStatus.PERMANENT);
        employee1.setStartDate(LocalDate.of(2023, 1, 15));
        employee1.setOnGoing(true);
        employee1.setHoursPerWeek(40);
        this.repo.save(employee1);
        employees.add(employee1);

        // Employee 2: Contract Employee
        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setMiddleName(null); // Middle name is optional (null)
        employee2.setLastName("Smith");
        employee2.setEmailAddress("jane.smith@example.com");
        employee2.setMobileNumber("0432 459 496");
        employee2.setStatus(Employee.EmployeeStatus.CONTRACT);
        employee2.setStartDate(LocalDate.of(2023, 3, 10));
        employee2.setOnGoing(false);
        employee2.setHoursPerWeek(20);
        this.repo.save(employee2);
        employees.add(employee2);
    }

    @Test
    public void getAllEmployees_EmployeesInDB_RetursSucess() {
        given()
            .when()
            .get("/employees")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(2))
                .body("firstName", hasItems("John", "Jane"));
    }
}
