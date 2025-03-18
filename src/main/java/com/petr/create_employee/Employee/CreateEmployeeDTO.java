package com.petr.create_employee.Employee;
import com.petr.create_employee.Employee.Employee.EmployeeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateEmployeeDTO {
    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotNull
    private EmployeeStatus status;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CreateEmployeeDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", status=" + status + "]";
    }

    
    
}
