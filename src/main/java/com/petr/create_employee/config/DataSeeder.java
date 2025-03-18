package com.petr.create_employee.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import com.petr.create_employee.Employee.Employee;
import com.petr.create_employee.Employee.EmployeeRepository;
import com.petr.create_employee.Employee.Employee.EmployeeStatus;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepo;
    private final Faker faker = new Faker();

    public DataSeeder(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(employeeRepo.count() == 0) { 
            for(int i = 0; i < 20; i++) { 
                String firstName = faker.name().firstName();
                String middletName = faker.name().firstName();
                String lasttName = faker.name().lastName();
                EmployeeStatus status = faker.options().option(Employee.EmployeeStatus.class);
                Employee fakEmployee = new Employee(firstName, middletName, lasttName, status);
                this.employeeRepo.saveAndFlush(fakEmployee);
            }
        }
    }
    
}
