package com.petr.create_employee.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petr.create_employee.common.exceptions.NotFoundException;
import com.petr.create_employee.common.exceptions.ServiceValidationException;
import com.petr.create_employee.common.exceptions.DuplicateEmailException;
import com.petr.create_employee.common.exceptions.DuplicateMobileException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    } 

    @ExceptionHandler(ServiceValidationException.class)
    public ResponseEntity<ValidationErrors> handleServiceValidationException(ServiceValidationException ex) { 
        return new ResponseEntity<ValidationErrors>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateMobileException.class)
    public ResponseEntity<String> handleDuplicateMobileException(DuplicateMobileException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
