package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee find(int id){
        return employeeRepository.findById(id).get();
    }

    public void save(Employee e){
        employeeRepository.save(e);
    }

    public void updateSalary(int id, int salary){
        Employee employee = employeeRepository.findById(id).get();
        employee.setSalary(salary);
        employeeRepository.save(employee);
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
}
