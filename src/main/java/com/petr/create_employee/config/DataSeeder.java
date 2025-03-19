package com.petr.create_employee.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

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
            Set<String> emails = new HashSet<String>();
            Set<String> numbers = new HashSet<String>();



            for(int i = 0; i < 20; i++) { 
                String firstName = faker.name().firstName();
                String middletName = faker.name().firstName();
                String lastName = faker.name().lastName();
                LocalDate startDate = LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault());
                Boolean onGoing = faker.random().nextBoolean();
                Integer hoursPerWeek = faker.number().numberBetween(10, 40);
                EmployeeStatus status = faker.options().option(Employee.EmployeeStatus.class);
                String emailAddress = faker.internet().emailAddress();
                String mobile = faker.phoneNumber().cellPhone();
                if(emails.contains(emailAddress) || numbers.contains(mobile)) { 
                    continue;
                }
                Employee fakeEmployee = new Employee(firstName, middletName, lastName, emailAddress, mobile, status, startDate, onGoing, hoursPerWeek);
                this.employeeRepo.saveAndFlush(fakeEmployee);
            }
        }
    }
    
}