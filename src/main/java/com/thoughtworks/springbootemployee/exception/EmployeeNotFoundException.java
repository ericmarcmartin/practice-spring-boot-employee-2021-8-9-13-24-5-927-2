package com.thoughtworks.springbootemployee.exception;

import static java.lang.String.format;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Integer id) {
        super(format("Employee ID %d not found.", id));
    }
}
