package com.company.service;

import com.company.model.Course;
import com.company.model.Student;

import java.util.ArrayList;

public interface CourseService {
    ArrayList<Course> findCourseByIndex(String Index);

    

    void changeCourseInList(String classType, String index, String line);

    ArrayList<String> findIndexesByCode(String code);

    void updateCourseDAT();

	void addStudent(String index, Student student);

	void dropStudent(String index, Student student);


	boolean soloSwap(Student student1, String oldIndex, String newIndex);

	boolean peerSwap(Student student1, Student student2, String oldIndex, String newIndex);

	boolean addCourseIntoList(Course course);

}
