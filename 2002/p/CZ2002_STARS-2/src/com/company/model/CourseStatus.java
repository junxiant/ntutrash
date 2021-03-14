package com.company.model;

import java.io.Serializable;

public class CourseStatus implements Serializable {

    private String index;
    private String status;

    public CourseStatus(String index, String status) {
        this.index = index;
        this.status = status;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
    	System.out.println("Updating " + this.status + " to " + status);
        this.status = status;
    }
}
