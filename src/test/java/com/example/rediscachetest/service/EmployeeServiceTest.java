package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService toTest;

    @Captor
    ArgumentCaptor<Integer> highRatingCaptor;

    List<Employee> employeeList;

    @BeforeEach
    public void setUp() {
//        initMocks() not required if we use @ExtendWith(MockitoExtension.class)
//        MockitoAnnotations.initMocks(this);
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
        Map<Integer, Collection<Employee>> ratingEmployeeGroupByMap = toTest.findHighRatedEmployeesBySalary(500);

        // two ways to check argument value - 1. ArgumentCaptor, 2. ArgumentMatcher(ex - eq())
        // below example shows, code should pass 5 as a threshold for considering a rating as high, it can also be done by eq(5)
        verify(employeeRepository).findBySalaryAndRating(eq(500), highRatingCaptor.capture());
        assertEquals(2, ratingEmployeeGroupByMap.size());
        assertEquals(5, highRatingCaptor.getValue());
    }
}