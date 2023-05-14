package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    public boolean pay(Employee employee, int amount){
        logger.info("paid Rs {} to employee {}", amount, employee.getEmail());
        return true;
    }
}
