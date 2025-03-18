package com.petr.create_employee.Employee;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository repo;
    private ModelMapper mapper;

    public EmployeeService(EmployeeRepository repo, ModelMapper mapper) { 
        this.repo = repo;
        this.mapper = mapper;
    }
    

    public Employee createEmployee(CreateEmployeeDTO data) {
        Employee newEmployee = mapper.map(data, Employee.class);
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

    public Optional<Employee> updateById(Long id, UpdateEmployeeDTO data) {
        Optional<Employee> result = this.getById(id);
        if(result.isEmpty()) {
            return result;
        }
        Employee foundEmployee = result.get();
        // if(data.getFirstName() != null) {
        //     foundEmployee.setFirstName(data.getFirstName().trim());
        // }

        // if(data.getMiddleName() != null) { 
        //     foundEmployee.setMiddleName(data.getMiddleName().trim());
        // }

        // if(data.getLastName() != null) { 
        //     foundEmployee.setLastName(data.getLastName().trim());
        // }
        // if(data.getStatus() != null) { 
        //     foundEmployee.setStatus(data.getStatus());
        // }
        mapper.map(data, foundEmployee);
        this.repo.save(foundEmployee);
        return Optional.of(foundEmployee);
    }
    
}
