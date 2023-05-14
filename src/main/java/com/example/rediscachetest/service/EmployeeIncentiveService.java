package com.example.rediscachetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeIncentiveService {

    IncentiveState currentIncentiveState;

    @Autowired
    @Qualifier("enrichIncentiveState")
    IncentiveState enrichedIncentiveState;

    @Autowired
    @Qualifier("incentivePayoutState")
    IncentiveState payoutIncentiveState;
}
