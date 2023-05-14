package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.model.PaymentStatus;
import org.springframework.stereotype.Component;

@Component("incentivePayoutState")
public class IncentivePayoutState implements IncentiveState {

    @Override
    public void rate(Employee employee, int rating) {

    }

    @Override
    public int calculateIncentive(int rating) {
        return 0;
    }

    @Override
    public PaymentStatus payOutIncentives(Employee employee) {
        return null;
    }
}
