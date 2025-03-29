package com.petr.create_employee.Employee;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.petr.create_employee.Employee.Employee.EmployeeStatus;
import com.petr.create_employee.common.ValidationErrors;
import com.petr.create_employee.common.exceptions.DuplicateEmailException;
import com.petr.create_employee.common.exceptions.DuplicateMobileException;
import com.petr.create_employee.common.exceptions.ServiceValidationException;
@Service
public class EmployeeService {

    private EmployeeRepository repo;
    private ModelMapper mapper;

    public EmployeeService(EmployeeRepository repo, ModelMapper mapper) { 
        this.repo = repo;
        this.mapper = mapper;
    } 
    public Employee createEmployee(CreateEmployeeDTO data) throws DuplicateEmailException, ServiceValidationException {
        final int FULL_TIME_HOURS = 40;
        final int MAX_PART_TIME_HOURS = 35;
        EmployeeStatus employeeStatus = data.getEmployeeStatus();
        Integer hours = data.getHoursPerWeek();
        ValidationErrors errors = new ValidationErrors();

        if(repo.existsByEmailAddress(data.getEmailAddress())) {
            //throw new DuplicateEmailException("Email address already exists: " + data.getEmailAddress());
            errors.addError("employee", "Email address already exists");
        }
        if(repo.existsByMobileNumber(data.getMobileNumber())) {
            //throw new DuplicateMobileException("Mobile number already exists: " + data.getMobileNumber());
            errors.addError("employee", "Mobile number already exists");
        }

        boolean isValid = switch (employeeStatus) {
            case PERMANENT_FULL_TIME -> hours == FULL_TIME_HOURS;
            case PERMANENT_PART_TIME -> hours > 0 && hours <= MAX_PART_TIME_HOURS;
            case CONTRACT, CASUAL -> hours > 0 && hours <= FULL_TIME_HOURS;
            default -> false;
        };
        if(!isValid) {
            String message = switch (employeeStatus) {
                case PERMANENT_FULL_TIME -> "Permanent full-time employees must work exactly 40 hours";
                case PERMANENT_PART_TIME -> "Permanent part-time employees must work 1-35 hours";
                case CONTRACT, CASUAL -> "Contract/Casual employees must work 1-40 hours";
                default -> "Invalid employee status";
            };
            errors.addError("employee", message);
        }

        if(errors.hasErrors()) {
            System.out.println("HAS ERRORRS");
            throw new ServiceValidationException(errors);
        }


        Employee newEmployee = mapper.map(data, Employee.class);
        this.repo.saveAndFlush(newEmployee);
        return newEmployee;
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
        mapper.map(data, foundEmployee);
        if(data.getEndDate() == null) {
            foundEmployee.setEndDate(null);
        }
        this.repo.save(foundEmployee);
        return Optional.of(foundEmployee);
        // mapper.map(data, foundEmployee);
        // return Optional.of(repo.save(foundEmployee));
    }
    
}
