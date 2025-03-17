package com.petr.create_employee.Employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> postMethodName(@RequestBody @Valid CreateEmployeeDTO data) {
        Employee newEmployee = this.employeeService.createEmployee(data);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }
    
}
