package com.company.repository.impl;

import com.company.model.Professor;
import com.company.model.Student;
import com.company.repository.ProfRespository;
import com.company.utils.PasswordUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProfessorRepositoryImpl implements ProfRespository {

    private ArrayList<Professor> allProfessors = new ArrayList<>();

    public ProfessorRepositoryImpl() {
        loadAllProfessorTXT();
        //loadAllProfessorsDAT();
        updatePorfessorDAT();
    }

    private void loadAllProfessorTXT() {
        try (Scanner scanner = new Scanner(new FileInputStream("com/company/file/professor.txt"))) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();

                
                StringTokenizer star = new StringTokenizer(nextLine, ",");
                String name = star.nextToken().trim();
                String email = star.nextToken().trim();
                int contact = Integer.parseInt(star.nextToken().trim());
                String password = star.nextToken().trim();
                password = PasswordUtil.caesarCipherEncrypt(password);
                Professor professor = new Professor(name, email, contact, password);
                allProfessors.add(professor);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllProfessorsDAT() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream("com/company/file/professor.dat");
            in = new ObjectInputStream(fis);
            allProfessors = (ArrayList) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Professor> getAllProfessors() {
        return this.allProfessors;
    }

    @Override
    public Professor findProfessionByName(String email) {
        for (Professor p : this.allProfessors) {
        	
            if (email.equals(p.getEmail())) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void updatePorfessorDAT() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("com/company/file/professor.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(allProfessors);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
