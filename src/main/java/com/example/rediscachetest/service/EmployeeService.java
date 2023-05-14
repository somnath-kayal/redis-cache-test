package com.example.rediscachetest.service;

import com.example.rediscachetest.exception.EmployeeNotFoundException;
import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee find(int id) throws EmployeeNotFoundException {
//        return employeeRepository.findById(id).get();
        return employeeRepository.findById(id).orElseThrow(() -> {
            return new EmployeeNotFoundException("Employee not found with id "+ id);
        });
    }

    public void save(Employee e){
        employeeRepository.save(e);
    }

    public Employee updateSalary(int id, int salary) {
        // difference between orElse() and orElseGet() is that orElse() will always be executed if the
        // Optional<T> is null or not, But orElseGet() will only be executed when Optional<T> is null.
        Employee employee = employeeRepository.findById(id).orElseGet(() -> {
            try {
                return find(id);
            } catch (EmployeeNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        employee.setSalary(salary);
        return employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    public void deleteAll(){
        employeeRepository.deleteAll();
    }

    public List<String> getAllEmployeeNames(){
        return employeeRepository.findAll().stream().map(Employee::getName).collect(Collectors.toList());
    }

    public List<String> getAllEmployeeEmail() {
        return employeeRepository.findAll().stream().map(Employee::getEmail).collect(Collectors.toList());
    }

    public Map<Integer,Employee> getAllEmployeeMap() {
        return employeeRepository.findAll().stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

    public List<Employee> getEmployeesAboveAge(int age){
        return employeeRepository.findFirst10ByAgeGreaterThanOrderByDateOfBirthDesc(age);
    }

    public Map<Integer, List<Employee>> getEmployeesAboveAgeGroupBy(int age){
        List<Employee> employees = employeeRepository.findFirst10ByAgeGreaterThanOrderByDateOfBirthDesc(age);
        return employees.stream().collect(
                Collectors.groupingBy(Employee::getAge));
    }

    public Map<Integer, Collection<Employee>> findHighRatedEmployeesBySalary(int salary){
        // get employees whose rating > 7 and group by rating and sort the values by salary for each group
        List<Employee> employees = employeeRepository.findBySalaryAndRating(salary, 5);
        logger.info("findHighRatedEmployees : {}", employees);
        return employees
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getRating,
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder())))
                        )
                );
    }

    public Employee updateRating(int employeeId, int rating) throws EmployeeNotFoundException {
        Employee e = find(employeeId);
        e.setRating(rating);
        return employeeRepository.save(e);

    }

    public List<Employee> findHighRatedEmployees(){
        return employeeRepository.findByRatingGreaterThanEqual(7);
    }
}
