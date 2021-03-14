package com.company.service.impl;

import com.company.model.Course;
import com.company.model.Professor;
import com.company.repository.ProfRespository;
import com.company.service.ProfService;

public class ProfServiceImpl implements ProfService {
    private ProfRespository profRepository;

    public ProfServiceImpl(ProfRespository profRespository) {
        this.profRepository = profRespository;
    }

    @Override
    public Professor findProfessorByName(String professorName) {
        return profRepository.findProfessionByName(professorName);
    }

    public void updatePorfessorDAT() {
        profRepository.updatePorfessorDAT();
    }
}
