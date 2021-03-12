package com.company.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    private String name;
    public String matricNumber;
    private String gender;
    private String nationality;
    private String password;
    private ArrayList<CourseStatus> timetable = new ArrayList<>();
    private int totalAu = 0;

    public Student(String name, String gender, String matricNumber, String nationality, String password) {
        this.name = name;
        this.gender = gender;
        this.matricNumber = matricNumber;
        this.nationality = nationality;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<CourseStatus> getTimetable() {
        return this.timetable;
    }

    public void setTimetable(ArrayList<CourseStatus> timetable) {
        this.timetable = timetable;
    }

    public int getTotalAu() {
        return totalAu;
    }

    public void setTotalAu(int totalAu) {
        this.totalAu = totalAu;
    }
}
