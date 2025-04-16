package com.petr.create_employee.Employee;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeEndToEndTest {
    private static final Logger log = LoggerFactory.getLogger(EmployeeEndToEndTest.class);
    private static final String EMPLOYEE1_FIRST_NAME = "Petr";
    private static final String EMPLOYEE1_MIDDLE_NAME = "Thomas";
    private static final String EMPLOYEE1_LAST_NAME = "Valouch";
    private static final String EMPLOYEE1_EMAIL = "petr@example.com";
    private static final String EMPLOYEE1_PHONE = "01234567890";
    private static final String EMPLOYEE1_ADDRESS = "2 Glebe Point Road, Glebe";
    private static final LocalDate EMPLOYEE1_START_DATE = LocalDate.of(2023, 1, 15);
    private static final int EMPLOYEE1_HOURS = 40;

    private static final String EMPLOYEE2_FIRST_NAME = "Jane";
    private static final String EMPLOYEE2_LAST_NAME = "Smith";
    private static final String EMPLOYEE2_EMAIL = "jane.smith@example.com";
    private static final String EMPLOYEE2_PHONE = "0432459496";
    private static final String EMPLOYEE2_ADDRESS = "155 Bridge Road, Glebe";
    private static final LocalDate EMPLOYEE2_START_DATE = LocalDate.of(2023, 3, 10);
    private static final LocalDate EMPLOYEE2_END_DATE = LocalDate.of(2025, 3, 10);
    private static final int EMPLOYEE2_HOURS = 20;

    @LocalServerPort
    private int port;

    private final ArrayList<Employee> employees = new ArrayList<>();

    @Autowired
    private EmployeeRepository repo;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        log.info("Running test: {}", testInfo.getDisplayName());
        RestAssured.port = port;
        repo.deleteAll();
        employees.clear();

        employees.add(createTestEmployee1());
        employees.add(createTestEmployee2());
    }

    @AfterEach
    public void tearDown() {
        repo.deleteAll();
    }

    private Employee createTestEmployee1() {
        Employee employee = new Employee();
        employee.setFirstName(EMPLOYEE1_FIRST_NAME);
        employee.setMiddleName(EMPLOYEE1_MIDDLE_NAME);
        employee.setLastName(EMPLOYEE1_LAST_NAME);
        employee.setEmailAddress(EMPLOYEE1_EMAIL);
        employee.setMobileNumber(EMPLOYEE1_PHONE);
        employee.setAddress(EMPLOYEE1_ADDRESS);
        employee.setEmployeeStatus(Employee.EmployeeStatus.PERMANENT_FULL_TIME);
        employee.setStartDate(EMPLOYEE1_START_DATE);
        employee.setHoursPerWeek(EMPLOYEE1_HOURS);
        return repo.save(employee);
    }

    private Employee createTestEmployee2() {
        Employee employee = new Employee();
        employee.setFirstName(EMPLOYEE2_FIRST_NAME);
        employee.setLastName(EMPLOYEE2_LAST_NAME);
        employee.setEmailAddress(EMPLOYEE2_EMAIL);
        employee.setMobileNumber(EMPLOYEE2_PHONE);
        employee.setAddress(EMPLOYEE2_ADDRESS);
        employee.setEmployeeStatus(Employee.EmployeeStatus.CONTRACT);
        employee.setStartDate(EMPLOYEE2_START_DATE);
        employee.setEndDate(EMPLOYEE2_END_DATE);
        employee.setHoursPerWeek(EMPLOYEE2_HOURS);
        return repo.save(employee);
    }

    @Nested
    class GetAllEmployeesTests {
        @Test
        void whenEmployeesExist_thenReturnsListOfEmployees() {
            given()
                .when()
                .get("/employees")
                .then()
                    .statusCode(OK.value())
                    .contentType(JSON)
                    .body("$", hasSize(2))
                    .body("firstName", hasItems(EMPLOYEE1_FIRST_NAME, EMPLOYEE2_FIRST_NAME))
                    .body("lastName", hasItems(EMPLOYEE1_LAST_NAME, EMPLOYEE2_LAST_NAME))
                    .body("emailAddress", hasItems(EMPLOYEE1_EMAIL, EMPLOYEE2_EMAIL));
        }
    }

    @Nested
    class GetEmployeeByIdTests {
        @Test
        void whenEmployeeExists_thenReturnsEmployee() {
            Long id = employees.get(0).getId();
            
            given()
                .pathParam("id", id)
            .when()
                .get("/employees/{id}")
            .then()
                .statusCode(OK.value())
                .body("id", equalTo(id.intValue()))
                .body("firstName", equalTo(EMPLOYEE1_FIRST_NAME))
                .body("lastName", equalTo(EMPLOYEE1_LAST_NAME));
        }

        @Test
        void whenEmployeeNotExists_thenReturnsNotFound() {
            given()
                .pathParam("id", 9999)
            .when()
                .get("/employees/{id}")
            .then()
                .statusCode(NOT_FOUND.value());
        }
    }

    @Nested
    class CreateEmployeeTests {
        @Test
        void whenValidPermanentEmployee_thenReturnsCreated() {
            given()
                .contentType(JSON)
                .body(validPermanentEmployeeRequest())
            .when()
                .post("/employees")
            .then()
                .statusCode(CREATED.value())
                .body("employeeStatus", equalTo("PERMANENT_FULL_TIME"))
                .body("endDate", nullValue());
                //.header("Location", containsString("/employees/"));
        }

        @Test
        void whenContractEmployeeWithEndDate_thenReturnsCreated() {
            given()
                .contentType(JSON)
                .body(validContractEmployeeRequest())
            .when()
                .post("/employees")
            .then()
                .statusCode(CREATED.value())
                .body("employeeStatus", equalTo("CONTRACT"))
                .body("endDate", notNullValue());
        }

        @Test
        void whenMissingRequiredFields_thenReturnsBadRequest() {
            given()
                .contentType(JSON)
                .body(missingRequiredFieldsRequest())
            .when()
                .post("/employees")
            .then()
                .statusCode(BAD_REQUEST.value());
        }

        @Test
        void whenInvalidHoursPerWeek_thenReturnsValidationError() {
            given()
                .contentType(JSON)
                .body(invalidHoursPerWeekRequest())
            .when()
                .post("/employees")
            .then()
                .statusCode(BAD_REQUEST.value());
        }

        private String validPermanentEmployeeRequest() {
            return """
                {
                    "firstName": "Alice",
                    "lastName": "Johnson",
                    "emailAddress": "alice@example.com",
                    "mobileNumber": "0456789012",
                    "address": "123 Long St",
                    "employeeStatus": "PERMANENT_FULL_TIME",
                    "startDate": "2023-01-01",
                    "hoursPerWeek": 40
                }
                """;
        }

        private String validContractEmployeeRequest() {
            return """
                {
                    "firstName": "Bob",
                    "lastName": "Brown",
                    "emailAddress": "bob@example.com",
                    "mobileNumber": "0456123456",
                    "address": "456 Oak Ave",
                    "employeeStatus": "CONTRACT",
                    "startDate": "2023-01-01",
                    "endDate": "2024-01-01",
                    "hoursPerWeek": 20
                }
                """;
        }

        private String missingRequiredFieldsRequest() {
            return """
                {
                    "firstName": "Alice",
                    "emailAddress": "alice@example.com",
                    "mobileNumber": "0456789012",
                    "address": "123 Main St",
                    "employeeStatus": "PERMANENT_FULL_TIME",
                    "startDate": "2023-01-01",
                    "hoursPerWeek": 40
                }
                """;
        }

        private String invalidHoursPerWeekRequest() {
            return """
                {
                    "firstName": "Alice",
                    "lastName": "Johnson",
                    "emailAddress": "alice@example.com",
                    "mobileNumber": "0456789012",
                    "address": "123 Main St",
                    "employeeStatus": "PERMANENT_FULL_TIME",
                    "startDate": "2023-01-01",
                    "hoursPerWeek": 50
                }
                """;
        }
    }

    @Nested
    class UpdateEmployeeTests {
        @Test
        void whenUpdateNonExistingEmployee_thenReturnsNotFound() {
            given()
                .contentType(JSON)
                .body("{}")
                .pathParam("id", 9999)
            .when()
                .patch("/employees/{id}")
            .then()
                .statusCode(NOT_FOUND.value());
        }
    }

    @Nested
    class DeleteEmployeeTests {
        @Test
        void whenDeleteExistingEmployee_thenReturnsNoContent() {
            Long id = employees.get(0).getId();
            
            given()
                .pathParam("id", id)
            .when()
                .delete("/employees/{id}")
            .then()
                .statusCode(NO_CONTENT.value());
        }

        @Test
        void whenDeleteNonExistingEmployee_thenReturnsNotFound() {
            given()
                .pathParam("id", 9999)
            .when()
                .delete("/employees/{id}")
            .then()
                .statusCode(NOT_FOUND.value());
        }

        @Test
        void whenDeleteThenGet_thenReturnsNotFound() {
            Long id = employees.get(0).getId();
            
            given().pathParam("id", id)
                .when().delete("/employees/{id}")
                .then().statusCode(NO_CONTENT.value());

            given().pathParam("id", id)
                .when().get("/employees/{id}")
                .then().statusCode(NOT_FOUND.value());
        }
    }
}