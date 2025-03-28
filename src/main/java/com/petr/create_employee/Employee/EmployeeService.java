package com.petr.create_employee.Employee;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.petr.create_employee.common.exceptions.DuplicateEmailException;
import com.petr.create_employee.common.exceptions.DuplicateMobileException;
@Service
public class EmployeeService {

    private EmployeeRepository repo;
    private ModelMapper mapper;

    public EmployeeService(EmployeeRepository repo, ModelMapper mapper) { 
        this.repo = repo;
        this.mapper = mapper;
    } 
    public Optional<Employee> createEmployee(CreateEmployeeDTO data) throws DuplicateEmailException {
        if(repo.existsByEmailAddress(data.getEmailAddress())) {
            throw new DuplicateEmailException("Email address already exists: " + data.getEmailAddress());
        }
        if(repo.existsByMobileNumber(data.getMobileNumber())) {
            throw new DuplicateMobileException("Mobile number already exists: " + data.getMobileNumber());
        }
        Employee newEmployee = mapper.map(data, Employee.class);
        this.repo.saveAndFlush(newEmployee);
        return Optional.of(newEmployee);
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
    public Optional<Employee> updateById(Long id, UpdateEmployeeDTO data) throws DuplicateMobileException {
        Optional<Employee> result = this.getById(id);
        if(result.isEmpty()) {
            return result;
        }
        Employee foundEmployee = result.get();
        if (!Objects.equals(data.getMobileNumber(), foundEmployee.getMobileNumber())) {
            if (repo.existsByMobileNumber(data.getMobileNumber())) {
                throw new DuplicateMobileException("Mobile number already in use.");
            }
        }
        if(!Objects.equals(data.getEmailAddress(), foundEmployee.getEmailAddress())) {
            if(repo.existsByEmailAddress(data.getEmailAddress())) {
                throw new DuplicateEmailException("Email already in use.");
            }
        }
        // mapper.map(data, foundEmployee);
        // if(data.getEndDate() == null) {
        //     foundEmployee.setEndDate(null);
        // }
        // this.repo.save(foundEmployee);
        // return Optional.of(foundEmployee);
        mapper.map(data, foundEmployee);
        return Optional.of(repo.save(foundEmployee));
    }
    
}
