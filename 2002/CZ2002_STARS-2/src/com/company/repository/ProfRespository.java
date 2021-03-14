package com.company.repository;

import com.company.model.Course;
import com.company.model.Professor;

public interface ProfRespository {
    Professor findProfessionByName(String name);

    void updatePorfessorDAT();
}
