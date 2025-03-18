package com.petr.create_employee.Employee;

import com.petr.create_employee.Employee.Employee.EmployeeStatus;
import jakarta.validation.constraints.Pattern;

public class UpdateEmployeeDTO {
    @Pattern(regexp = ".*\\S.*", message = "First name cannot be empty")
    private String firstName;

    @Pattern(regexp = ".*\\S.*", message = "First name cannot be empty")
    private String middleName;

    @Pattern(regexp = ".*\\S.*", message = "Last name cannot be empty")
    private String lastName;

    private EmployeeStatus status;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public UpdateEmployeeDTO() {
    }

    @Override
    public String toString() {
        return "UpdateEmployeeDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", status=" + status + "]";
    }
}
