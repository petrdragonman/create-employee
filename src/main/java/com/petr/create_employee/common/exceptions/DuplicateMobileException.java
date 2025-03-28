package com.petr.create_employee.common.exceptions;

public class DuplicateMobileException extends RuntimeException {
    public DuplicateMobileException(String message) {
        super(message);
    }
}
