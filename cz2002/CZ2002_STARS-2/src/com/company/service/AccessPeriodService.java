package com.company.service;

import com.company.model.AccessPeriod;
import com.company.repository.AccessPeriodRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public interface AccessPeriodService {
    AccessPeriod getAccessPeriod();

    void updateAccessPeriod();
}
