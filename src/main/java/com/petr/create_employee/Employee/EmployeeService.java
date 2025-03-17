package com.petr.create_employee.Employee;

import com.petr.create_employee.CreateEmployeeApplication;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository repo;

    EmployeeService(EmployeeRepository repo, CreateEmployeeApplication createEmployeeApplication) {
        this.repo = repo;
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
    
}
