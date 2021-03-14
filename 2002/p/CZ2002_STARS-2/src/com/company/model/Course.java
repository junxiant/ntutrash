package com.company.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    private String code;
    private String index;
    private Time time;
    private String classType;
    private String day;
    private String venue;
    private String group;
    private int vacancy;
    private int au;
    private int size;

    private String school;
    private ArrayList<Student> registeredList = new ArrayList<>(vacancy);
    private ArrayList<Student> waitList = new ArrayList<>();

    public Course(String school, String code, String index, Time time, String classType, String day, String venue, String group, int size, int au) {
        this.school = school;
        this.code = code;
        this.index = index;
        this.time = time;
        this.classType = classType;
        this.day = day;
        this.venue = venue;
        this.group = group;
        this.size = size;
        this.vacancy = size;
        this.au = au;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public int getAu() {
        return au;
    }

    public String getGroup() {
        return group;
    }

    public int getSize() {
        return size;
    }

    public String getSchool() {
        return school;
    }

    public ArrayList<Student> getRegisteredList() {
        return registeredList;
    }

    public void setRegisteredList(ArrayList<Student> registeredList) {
        this.registeredList = registeredList;
    }

    public ArrayList<Student> getWaitList() {
        return waitList;
    }

    public void setWaitList(ArrayList<Student> waitList) {
        this.waitList = waitList;
    }

	public void setAu(int au) {

		this.au = au;
	}
	
	public void setSize(int size) {

		this.size = size;
	}

	public void setGroup(String newGroup) {
		this.group = newGroup;
		
	}

	public void setSchool(String newSchool) {
		this.school = newSchool;
		
	}
}
