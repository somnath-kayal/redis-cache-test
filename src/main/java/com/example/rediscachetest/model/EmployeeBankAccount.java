package com.example.rediscachetest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EMPLOYEE_BANK_ACCOUNT_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeBankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ACCOUNT_ID")
    @NotNull
    private String bankAccountId;

    @Column(name = "IFSC")
    @NotNull
    private String ifsc;

    @OneToOne(mappedBy = "bankAccount")
    private Employee employee;
}
