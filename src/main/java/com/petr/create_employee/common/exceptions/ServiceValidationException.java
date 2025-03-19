package com.petr.create_employee.common.exceptions;

import com.petr.create_employee.common.ValidationErrors;

public class ServiceValidationException extends Exception {
    private ValidationErrors errors;

    public ServiceValidationException(ValidationErrors errors) {
        this.errors = errors;
    }

    public ValidationErrors getErrors() {
        return errors;
    }
}
