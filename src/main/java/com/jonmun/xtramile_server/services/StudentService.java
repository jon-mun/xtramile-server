package com.jonmun.xtramile_server.services;

import java.util.List;
import java.util.Optional;

import com.jonmun.xtramile_server.domain.entities.StudentEntity;

public interface StudentService {
    StudentEntity save(StudentEntity studentEntity);

    List<StudentEntity> findAll();

    Optional<StudentEntity> findById(String id);

    boolean existsById(String id);

    StudentEntity updatePartial(String id, StudentEntity studentEntity);

    void deleteById(String id);
}
