package com.thoughtworks.springbootemployee.exception;

import static java.lang.String.format;

public class CompanyDoesNotExistException extends RuntimeException{
    public CompanyDoesNotExistException(String message){
        super(format("Employee ID %s not found.", message));
    }
}
