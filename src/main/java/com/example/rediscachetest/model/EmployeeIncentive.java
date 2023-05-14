package com.example.rediscachetest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "EMPLOYEE_INCENTIVE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeIncentive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incentive_id")
    private int id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "incentive_date")
    private LocalDateTime paidOn;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "employee_id")
    private Employee employee;
}
