package com.company.repository;

import com.company.model.Course;

import java.util.ArrayList;

public interface CourseRepository {
    ArrayList<Course> findCourseByIndex(String index);

    boolean addCourseIntoList(Course course);

//    void changeCourseInList(String index, String line);

    ArrayList<String> findIndexesByCode(String code);

    void updateCourseDAT();

	void changeCourseInList(String classtype, String index, String line);
}
