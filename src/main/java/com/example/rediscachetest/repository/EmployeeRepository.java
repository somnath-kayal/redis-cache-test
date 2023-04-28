package com.example.rediscachetest.repository;

import com.example.rediscachetest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Read more about possible custom methods
https://javatute.com/jpa/how-to-write-custom-method-in-repository-in-spring-data-jpa/
https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html

CRUD methods on repository instances are transactional by default. For reading operations
the transaction configuration readOnly flag is set to true, all others are configured with
a plain @Transactional so that default transaction configuration applies.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findFirst10ByAgeGreaterThanOrderByDateOfBirthDesc(int age);

    @Query("select e from Employee e where e.salary > :salary and e.rating > :rating")
    List<Employee> findBySalaryAndRating(@Param("salary") int salary, @Param("rating") int rating);
}
