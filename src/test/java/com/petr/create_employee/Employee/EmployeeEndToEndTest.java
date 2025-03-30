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

        // Employee 1: Permanent Full-Time
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setMiddleName("Michael");
        employee1.setLastName("Doe");
        employee1.setEmailAddress("john.doe@example.com");
        employee1.setMobileNumber("+1234567890");
        employee1.setAddress("2 Glebe Point Road, Glebe");
        employee1.setEmployeeStatus(Employee.EmployeeStatus.PERMANENT_FULL_TIME);
        employee1.setStartDate(LocalDate.of(2023, 1, 15));
        employee1.setHoursPerWeek(40);
        repo.save(employee1);
        employees.add(employee1);

        // Employee 2: Contract (with end date)
        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setEmailAddress("jane.smith@example.com");
        employee2.setMobileNumber("0432459496");
        employee2.setAddress("155 Bridge Road, Glebe");
        employee2.setEmployeeStatus(Employee.EmployeeStatus.CONTRACT);
        employee2.setStartDate(LocalDate.of(2023, 3, 10));
        employee2.setEndDate(LocalDate.of(2025, 3, 10));
        employee2.setHoursPerWeek(20);
        repo.save(employee2);
        employees.add(employee2);
    }

    //===== GET TESTS =====//
    @Test
    public void getAllEmployees_EmployeesInDB_RetursSucess() {
        given()
            .when()
            .get("/employees")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(2))
                .body("firstName", hasItems("John", "Jane"))
                .body("lastName", hasItems("Doe", "Smith"))
                .body("emailAddress", hasItems("john.doe@example.com", "jane.smith@example.com"));

    }

    //===== POST TESTS =====//
    @Test
    public void createEmployee_validPermanentEmployee_returnsCreated() {
        given()
            .contentType("application/json")
            .body("""
                {
                    "firstName": "Alice",
                    "lastName": "Johnson",
                    "emailAddress": "alice@example.com",
                    "mobileNumber": "0456789012",
                    "address": "123 Main St",
                    "employeeStatus": "PERMANENT_FULL_TIME",
                    "startDate": "2023-01-01",
                    "hoursPerWeek": 40
                }
                """)
        .when()
            .post("/employees")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("employeeStatus", equalTo("PERMANENT_FULL_TIME"))
            .body("endDate", nullValue());
    }

    @Test
    public void createEmployee_missingRequiredFields_returnsBadRequest() {
        given()
            .contentType("application/json")
            .body("""
                {
                    "firstName": "Alice",
                    "emailAddress": "alice@example.com",
                    "mobileNumber": "0456789012",
                    "address": "123 Main St",
                    "employeeStatus": "PERMANENT_FULL_TIME",
                    "startDate": "2023-01-01",
                    "hoursPerWeek": 40
                }
                """)
        .when()
            .post("/employees")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
            //.body("errors", hasItems("First name is required", "Email is required"));
    }

    @Test
    public void createEmployee_invalidHoursPerWeek_returnsValidationError() {
        given()
            .contentType("application/json")
            .body("""
                {
                    "firstName": "Alice",
                    "lastName": "Johnson",
                    "emailAddress": "alice@example.com",
                    "mobileNumber": "0456789012",
                    "address": "123 Main St",
                    "employeeStatus": "PERMANENT_FULL_TIME",
                    "startDate": "2023-01-01",
                    "hoursPerWeek": 38
                }
                """)
        .when()
            .post("/employees")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
            //.body("errors", hasItem("Hours per week cannot exceed 40"));
    }

    //===== PATCH TESTS =====//
    @Test
    public void updateEmployee_changeToContractWithEndDate_returnsUpdated() {
        Long id = employees.get(0).getId();
        given()
            .contentType("application/json")
            .body("""
                {
                    "employeeStatus": "CONTRACT"
                }
                """)
            .pathParam("id", id)
        .when()
            .patch("/employees/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("employeeStatus", equalTo("CONTRACT"));
            //.body("endDate", equalTo("2024-12-31"));
    }

    @Test
    public void updateEmployee_setInvalidEndDateBeforeStart_returnsBadRequest() {
        Long id = employees.get(0).getId();
        given()
            .contentType("application/json")
            .body("""
                {
                    "endDate": "2022-01-01"  // Before startDate (2023-01-15)
                }
                """)
            .pathParam("id", id)
        .when()
            .patch("/employees/{id}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
            //.body("errors", hasItem("End date must be after start date"));
    }

    //===== DELETE TESTS =====//
    @Test
    public void deleteEmployee_thenGet_returnsNotFound() {
        Long id = employees.get(0).getId();
        given().pathParam("id", id)
            .when().delete("/employees/{id}")
            .then().statusCode(HttpStatus.NO_CONTENT.value());

        given().pathParam("id", id)
            .when().get("/employees/{id}")
            .then().statusCode(HttpStatus.NOT_FOUND.value());
    }
}