package com.company.repository.impl;

import com.company.model.Student;
import com.company.repository.StudentRepository;
import com.company.utils.PasswordUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentRepositoryImpl implements StudentRepository {

    private ArrayList<Student> allStudents = new ArrayList<>();

    public StudentRepositoryImpl() {
        //loadAllStudentTXT();
        loadAllStudentDAT();
        //updateStudentDAT();
    }
    
    // Method to load data from .txt file, it is used initially to set up the .DAT files rather than manually input student by student
    private void loadAllStudentTXT() {
        try (Scanner scanner = new Scanner(new FileInputStream("com/company/file/student.txt"))) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                StringTokenizer star = new StringTokenizer(nextLine, ",");
                String name = star.nextToken().trim();
                String gender = star.nextToken().trim();
                String marticNumber = star.nextToken().trim();
                String nationality = star.nextToken().trim();
                String password = star.nextToken().trim();
                password = PasswordUtil.caesarCipherEncrypt(password);
                Student student = new Student(name, gender, marticNumber, nationality, password);
                allStudents.add(student);
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Method to load data from .dat file    
    private void loadAllStudentDAT() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream("com/company/file/student.dat");
            in = new ObjectInputStream(fis);
            allStudents = (ArrayList) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    // Method to save data to .dat file 
    public void updateStudentDAT() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("com/company/file/student.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(allStudents);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return this.allStudents;
    }
    
    // Public method to add student to database, only called by service layer
    @Override
    public ArrayList<Student> addStudentIntolist(String name, String gender, String matricNumber, String nationality, String password) {

        Student student = new Student(name, gender, matricNumber, nationality, password);
        allStudents.add(student);
        return allStudents;
    }

    // Public method to find student by matriculation number, only called by service layer
    @Override
    public Student findStudentByMatricNumber(String matricNumber) {
        for (Student s : this.allStudents) {
            if (matricNumber.equals(s.getMatricNumber())) {
                return s;
            }
        }
        return null;
    }
}


