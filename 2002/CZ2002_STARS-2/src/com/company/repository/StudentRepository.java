package com.company.repository;

import com.company.model.Student;

import java.util.ArrayList;

public interface StudentRepository {
    Student findStudentByMatricNumber(String matricNumber);

    ArrayList<Student> getAllStudents();

//    void addStudentIntolist(String line);

    void updateStudentDAT();

	ArrayList<Student> addStudentIntolist(String name, String gender, String matricNumber, String nationality, String password);
}
