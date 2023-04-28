package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService toTest;

    List<Employee> employeeList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Employee e1 = new Employee();
        e1.setId(123);
        e1.setSalary(1000);
        e1.setRating(8);

        Employee e2 = new Employee();
        e2.setId(124);
        e2.setSalary(2000);
        e2.setRating(7);

        Employee e3 = new Employee();
        e3.setId(125);
        e3.setSalary(3000);
        e3.setRating(8);

        Employee e4 = new Employee();
        e4.setId(126);
        e4.setSalary(4000);
        e4.setRating(8);

        employeeList = Arrays.asList(e1, e2, e3, e4);
    }

    @Test
    void findHighRatedEmployees(){
        doReturn(employeeList).when(employeeRepository).findBySalaryAndRating(anyInt(), anyInt());
        Map<Integer, Collection<Employee>> ratingEmployeeGroupByMap = toTest.findHighRatedEmployees(500);
        verify(employeeRepository).findBySalaryAndRating(eq(500), eq(5));
        assertEquals(2, ratingEmployeeGroupByMap.size());
    }
}