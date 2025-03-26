package com.petr.create_employee.Employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petr.create_employee.common.exceptions.DuplicateEmailException;
import com.petr.create_employee.common.exceptions.NotFoundException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid CreateEmployeeDTO data) throws DuplicateEmailException {
        Optional<Employee> result = this.employeeService.createEmployee(data);
        Employee newEmployee = result.orElseThrow(() -> new DuplicateEmailException("Email address already exists."));
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = this.employeeService.getAll();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) throws NotFoundException {
        Optional<Employee> result = this.employeeService.getById(id);
        Employee foundEmployee = result.orElseThrow(() -> new NotFoundException("Could not find Employee with id: " + id));
        return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateById(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeDTO data) throws NotFoundException {
        Optional<Employee> result = this.employeeService.updateById(id, data);
        Employee foundEmployee = result.orElseThrow(() -> new NotFoundException("Could not update Employee with id: " + id));
        return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) throws NotFoundException {
        boolean wasDeleted = this.employeeService.deleteById(id);
        if(!wasDeleted) {
            throw new NotFoundException("Could not delete Employee with id: " + id + " id does not exists.");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

    
    
}
