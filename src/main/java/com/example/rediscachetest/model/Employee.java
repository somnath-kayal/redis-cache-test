package com.example.rediscachetest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Employee implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "name")
    @NotNull(message = "employee name can't be null")
    @NotBlank
    private String name;

    @Column(name = "email")
    @Email(message = "Employee email is required")
    private String email;

    @Column(name = "salary")
    private int salary;

    @Column(name = "age")
    @Min(18)
    @Max(60)
    private int age;

    @Column(name = "DOB")
    @Past
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull
//    @Pattern(regexp = "^\\d{10}$", message = "contact number not valid")
    private int contact;

    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private int rating;
}
