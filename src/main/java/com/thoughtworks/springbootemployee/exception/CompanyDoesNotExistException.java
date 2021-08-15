package com.thoughtworks.springbootemployee.exception;

import static java.lang.String.format;

public class CompanyDoesNotExistException extends RuntimeException {
    public CompanyDoesNotExistException(Integer id) {
        super(format("Company ID %s not found.", id));
    }
}
