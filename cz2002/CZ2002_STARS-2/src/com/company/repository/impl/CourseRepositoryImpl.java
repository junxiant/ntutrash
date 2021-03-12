package com.company.repository.impl;

import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Time;
import com.company.repository.CourseRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class CourseRepositoryImpl implements CourseRepository {
    private ArrayList<Course> allCourses = new ArrayList<>();

    public CourseRepositoryImpl() {
    	//loadAllCoursesTXT();
        loadAllCoursesDAT();
//        updateCourseDAT(); 
    }

    private void loadAllCoursesTXT() {
        try (Scanner scanner = new Scanner(new FileInputStream("com/company/file/course.txt"))) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                StringTokenizer star = new StringTokenizer(nextLine, ",");
                String school = star.nextToken().trim();
                String code = star.nextToken().trim();
                String index = star.nextToken().trim();
                String timeString = star.nextToken().trim();
                String classType = star.nextToken().trim();
                String day = star.nextToken().trim();
                String venue = star.nextToken().trim();
                String group = star.nextToken().trim();
                int size = Integer.parseInt(star.nextToken().trim());
                int au = Integer.parseInt(star.nextToken().trim());

                Time time = new Time();
                time.setStartHour(Integer.parseInt(timeString.substring(0, 2)));
                time.setEndHour(Integer.parseInt(timeString.substring(5, 7)));
                time.setStartMinute(Integer.parseInt(timeString.substring(2, 4)));
                time.setEndMinute(Integer.parseInt(timeString.substring(7, 9)));
                
                Course course = new Course(school, code, index, time, classType, day, venue, group, size, au);
                allCourses.add(course);
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllCoursesDAT() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream("com/company/file/course.dat");
            in = new ObjectInputStream(fis);
            allCourses = (ArrayList) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Course> getallCourses() {
        return this.allCourses;
    }

    @Override
    public ArrayList<Course> findCourseByIndex(String index) {
    	ArrayList<Course> classes = new ArrayList<>();
        for (Course s : this.allCourses) {
            if (index.equals(s.getIndex())) {
                classes.add(s);
            }
        }
        return classes;
    }

    @Override
    public ArrayList<String> findIndexesByCode(String code) {
        ArrayList<String> indexes = new ArrayList<>();
        for (Course s : this.allCourses) {
            if (code.equals(s.getCode())) {
            	if (!indexes.contains(s.getIndex())) {
            		indexes.add(s.getIndex());
            	}
                
            }
        }
        return indexes;
    }

    @Override
    public boolean addCourseIntoList(Course course) {

        allCourses.add(course);
        
        return true;
    }

    @Override
    public void changeCourseInList(String classtype, String index, String line) {

    }

    @Override
    public void updateCourseDAT() {

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("com/company/file/course.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(allCourses);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
