package com.example.rediscachetest.exception;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
