package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.model.PaymentStatus;

public class RateEmployeeState implements IncentiveState {
    @Override
    public int calculateIncentive(int rating) {
        return 0;
    }

    @Override
    public PaymentStatus payOutIncentives(Employee employee) {
        return null;
    }
}
