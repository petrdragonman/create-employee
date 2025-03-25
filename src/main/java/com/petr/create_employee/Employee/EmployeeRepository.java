package com.petr.create_employee.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    boolean existsByEmailAddress(String emailAddress);
    boolean existsByMobileNumber(String mobileNumber);
}
