package com.example.rediscachetest.resource;

import com.example.rediscachetest.exception.EmployeeNotFoundException;
import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    public static final String MY_KEY = "empEmails";

    @Cacheable(value = "employees")
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> fetchAll(){
        logger.info("Getting all employees");
        return employeeService.getAllEmployees();
    }

    @Cacheable(value = "employees", key = "#id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee get(@PathVariable("id") int id) throws EmployeeNotFoundException {
        logger.info("Getting employee with id {}",id);
        return employeeService.find(id);
    }

    @CachePut(value = "employees", key = "#employee.id", condition = "#employee.salary > 15000")
    @PostMapping("/save")
    public @ResponseBody ResponseEntity<String> save(@RequestBody @Valid Employee employee) {
        logger.info("Saving employee");
        employeeService.save(employee);
        return new ResponseEntity<String>("Saved Successfully", HttpStatus.OK);
    }

    @CachePut(value = "employees", key = "#id")
    @GetMapping("/update/{id}")
    public @ResponseBody ResponseEntity<Employee> updateSalary(@PathVariable("id") int id,@RequestParam int salary){
        logger.info("updating salary {} for employee {}",salary,id);
        Employee e = employeeService.updateSalary(id,salary);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @CacheEvict(value = "employees", key = "#id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        logger.info("deleting employee {}",id);
        employeeService.delete(id);
        return new ResponseEntity<String>("deleted Successfully", HttpStatus.OK);
    }

    @CacheEvict(value = "employees", allEntries = true)
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll(){
        logger.info("deleting all records");
        employeeService.deleteAll();
        return new ResponseEntity<String>("deleted all employees", HttpStatus.OK);
    }

    @CacheEvict(value = "employees", allEntries = true)
    @GetMapping("/clear-cache")
    public ResponseEntity<String> clearCache(){
        logger.info("clearing cache");
        return new ResponseEntity<String>("cache cleared", HttpStatus.OK);
    }

    @Cacheable(value = "employees", key = "#root.methodName")
    @GetMapping(path = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> fetchAllEmployeeNames(){
        logger.info("Fetching all employee names");
        return employeeService.getAllEmployeeNames();
    }

    @Cacheable(value = "employees", key = "#root.target.MY_KEY")
    @GetMapping(path = "/emails", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> fetchAllEmployeeEmails(){
        logger.info("Fetching all employee emails");
        return employeeService.getAllEmployeeEmail();
    }

    @Cacheable(value = "employees", key = "#root.methodName")
    @GetMapping(path = "/all-map", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer,Employee> getAllEmployeesMap(){
        logger.info("Creating all employee map");
        return employeeService.getAllEmployeeMap();
    }

    @GetMapping(path = "/above-age/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesAboveAge(@PathVariable int age){
        logger.info("Getting all employee above age {}", age);
        return employeeService.getEmployeesAboveAge(age);
    }

    @GetMapping("/groupingBy-age/{age}")
    public Map<Integer,List<Employee>> getEmployeeAboveAgeGroupBy(@PathVariable int age){
        logger.info("Getting all employee above age {} by groupingBy age", age);
        return employeeService.getEmployeesAboveAgeGroupBy(age);
    }

    @GetMapping("/high-rated/salary/{salary}")
    public Map<Integer, Collection<Employee>> findHighRatedEmployees(@PathVariable int salary){
        return employeeService.findHighRatedEmployeesBySalary(salary);
    }
}
