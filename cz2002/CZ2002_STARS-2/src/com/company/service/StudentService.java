package com.company.service;

import java.util.ArrayList;

import com.company.model.Course;
import com.company.model.Student;


public interface StudentService {
    Student findStudentByMatric(String matricNumber);

    ArrayList<Student> addStudentIntolist(String name, String gender, String matricNumber, String nationality, String password);

    void updateStudentDAT();

	void addCourseIntoTimeTable(int vacancy, Student student, Course course);

	void dropCourseFromTimeTable(Student student, Course course);

	boolean soloSwap(Student student1, Course course1, Course course2, String oldIndex, String newIndex) ;

	boolean peerSwap(Student student1, Student student2, String oldIndex, String newIndex);
}
