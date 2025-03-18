package com.petr.create_employee.Employee;

import com.petr.create_employee.CreateEmployeeApplication;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final CreateEmployeeApplication createEmployeeApplication;

    private EmployeeRepository repo;

    EmployeeService(EmployeeRepository repo, CreateEmployeeApplication createEmployeeApplication) {
        this.repo = repo;
        this.createEmployeeApplication = createEmployeeApplication;
    }

    public Employee createEmployee(CreateEmployeeDTO data) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(data.getFirstName());
        if(data.getMiddleName() != null) {
            newEmployee.setMiddleName(data.getMiddleName().trim());
        }
        newEmployee.setLastName(data.getLastName().trim());
        newEmployee.setStatus(data.getStatus());
        return this.repo.saveAndFlush(newEmployee);
    }

    public List<Employee> getAll() {
        return this.repo.findAll();
    }

    public Optional<Employee> getById(Long id) {
        return this.repo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<Employee> result = this.getById(id);
        if (result.isEmpty()) {
            return false;
        }
        this.repo.delete(result.get());
        return true;
    }
    
}
