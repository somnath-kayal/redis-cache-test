package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.model.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("enrichIncentiveState")
public class EnrichIncentiveState implements IncentiveState {

    @Autowired
    private EmployeeService employeeService;

    public Map<Integer, List<Employee>> groupEmployeesByHighRating(){
        Map<Integer, List<Employee>> ratingEmployee = employeeService.findHighRatedEmployees()
                .stream()
                .collect(Collectors.groupingBy(
                        Employee::getRating,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                e -> new ArrayList<>(calculateIncentive(e)))));
        return ratingEmployee;
    }

    @Override
    public void rate(Employee employee, int rating) {

    }

    @Override
    public int calculateIncentive(int rating) {
        if(rating == 7 || rating == 8)
                return 5000;
        else if(rating > 8){
            return 8000;
        }
        return 0;
    }

    @Override
    public PaymentStatus payOutIncentives(Employee employee) {
        return null;
    }

}
