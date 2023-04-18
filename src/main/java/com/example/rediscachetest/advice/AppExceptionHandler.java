package com.example.rediscachetest.advice;

import com.example.rediscachetest.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidModelFields(MethodArgumentNotValidException ex){
        // Collectors.toMap() doesn't allow null for key and value, this a open bug in jdk
        // use forEach instead to put it manually if there is a possibility of getting null as key or value
        // https://www.onlinetutorialspoint.com/java8/resolve-nullpointerexception-in-collectors-tomap.html
        Map<String, String> res = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> res.put(e.getField(), e.getDefaultMessage()));
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleInvalidModelFields(EmployeeNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
