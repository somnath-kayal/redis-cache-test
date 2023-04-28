package com.example.rediscachetest.repository;

import com.example.rediscachetest.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository toTest;

    Employee employee;

    @BeforeEach
    public void setUp() throws Exception {
        employee = Employee.builder()
                .name("abc")
                .contact(1234)
                .rating(7)
                .age(20)
                .build();
    }

    @Test
    public void saveEmployee(){
        List<Employee> res = toTest.findAll();
        Employee em = toTest.save(employee);
        System.out.println(em);
        assertNotNull(em);
        assertNotNull(toTest.findAll().get(0));
        assertEquals(1, toTest.findAll().size());
    }
}