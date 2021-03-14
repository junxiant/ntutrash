package com.company.model;

import java.time.LocalDate;
import java.util.Date;

public class AccessPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    // Basic constructor with no initialized attributes
    public AccessPeriod() {}
    
    // Constructor Overloading with initialized attributes using inputs
    public AccessPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}
