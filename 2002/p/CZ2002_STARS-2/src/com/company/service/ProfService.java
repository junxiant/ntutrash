package com.company.service;
import com.company.model.Professor;

public interface ProfService {
    Professor findProfessorByName(String professorName);
}
