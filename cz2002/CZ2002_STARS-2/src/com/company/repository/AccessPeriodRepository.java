package com.company.repository;

import com.company.model.AccessPeriod;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface AccessPeriodRepository {

    void loadAccessPeriod();

    AccessPeriod getAccessPeriod();

    void updateAccessPeriod();
}
