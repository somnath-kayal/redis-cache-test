package com.example.rediscachetest.service;

import com.example.rediscachetest.model.Employee;
import com.example.rediscachetest.model.PaymentStatus;

public interface IncentiveState {

    void rate(Employee employee, int rating);
    int calculateIncentive(int rating);

    PaymentStatus payOutIncentives(Employee employee);

    public enum State{
        NO_RATING,
        INCENTIVE_ENRICHED,
        INCENTIVE_PAID
    }

}
