package com.company.model;

import java.io.Serializable;

public class Time implements Serializable {
    private int startHour;
    private int endHour;
    private int startMinute;
    private int endMinute;
    
    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getStartMinute() {
    	return this.startMinute;
    }
    
    public int getEndMinute() {
    	return this.endMinute;
    }
    
	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}
	
	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}
}
