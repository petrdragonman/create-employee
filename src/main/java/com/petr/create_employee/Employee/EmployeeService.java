package com.petr.create_employee.Employee;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.petr.create_employee.common.exceptions.DuplicateEmailException;
@Service
public class EmployeeService {

    private EmployeeRepository repo;
    private ModelMapper mapper;

    public EmployeeService(EmployeeRepository repo, ModelMapper mapper) { 
        this.repo = repo;
        this.mapper = mapper;
    } 
    public Employee createEmployee(CreateEmployeeDTO data) throws DuplicateEmailException {
        if(repo.existsByEmailAddress(data.getEmailAddress())) {
            throw new DuplicateEmailException("Email address already exists: " + data.getEmailAddress());
        }
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
        mapper.map(data, foundEmployee);
        this.repo.save(foundEmployee);
        return Optional.of(foundEmployee);
    }
    
}
