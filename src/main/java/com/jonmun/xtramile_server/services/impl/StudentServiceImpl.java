package com.jonmun.xtramile_server.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.jonmun.xtramile_server.domain.entities.StudentEntity;
import com.jonmun.xtramile_server.repositories.StudentRepository;
import com.jonmun.xtramile_server.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        if (studentEntity == null) {
            throw new RuntimeException("Student data cannot be null");
        }

        String studentId = Objects.requireNonNull(studentEntity.getId(), "Student id cannot be null");

        if (studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student already exists");
        }

        return studentRepository.save(studentEntity);
    }

    @Override
    public List<StudentEntity> findAll() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentEntity> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return studentRepository.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        if (id == null) {
            return false;
        }
        return studentRepository.existsById(id);
    }

    @Override
    public StudentEntity updatePartial(String id, StudentEntity studentEntity) {
        if (id == null) {
            throw new RuntimeException("Student id cannot be null");
        }

        studentEntity.setId(id);

        return studentRepository.findById(id).map(existingStudentEntity -> {
            Optional.ofNullable(studentEntity.getFirstName()).ifPresent(existingStudentEntity::setFirstName);
            Optional.ofNullable(studentEntity.getLastName()).ifPresent(existingStudentEntity::setLastName);
            Optional.ofNullable(studentEntity.getDob()).ifPresent(existingStudentEntity::setDob);
            return studentRepository.save(existingStudentEntity);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new RuntimeException("Student id cannot be null");
        }

        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }

        studentRepository.deleteById(id);
    }

}
