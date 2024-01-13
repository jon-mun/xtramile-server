package com.jonmun.xtramile_server.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.jonmun.xtramile_server.domain.dto.CreateStudentDto;
import com.jonmun.xtramile_server.domain.dto.GetStudentDto;
import com.jonmun.xtramile_server.domain.entities.StudentEntity;
import com.jonmun.xtramile_server.mappers.Mapper;
import com.jonmun.xtramile_server.services.StudentService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    private StudentService studentService;

    private Mapper<StudentEntity, CreateStudentDto> studentMapper;

    public StudentController(StudentService studentService, Mapper<StudentEntity, CreateStudentDto> studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @PostMapping("/students")
    public ResponseEntity<CreateStudentDto> createStudent(
            @Valid @RequestBody CreateStudentDto studentDto) {
        StudentEntity studentEntity = studentMapper.mapFrom(studentDto);

        studentEntity = studentService.save(studentEntity);

        return new ResponseEntity<>(studentMapper.mapTo(studentEntity), HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public List<GetStudentDto> getStudents() {
        List<StudentEntity> students = studentService.findAll();

        return students.stream()
                .map(studentEntity -> new GetStudentDto(
                        studentEntity.getId(),
                        studentEntity.getFullName(),
                        studentEntity.getAge()))
                .collect(Collectors.toList());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<CreateStudentDto> getStudentById(@PathVariable("id") String id) {
        StudentEntity studentEntity = studentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return new ResponseEntity<>(studentMapper.mapTo(studentEntity), HttpStatus.OK);
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<CreateStudentDto> updateStudent(
            @PathVariable("id") String id,
            @RequestBody CreateStudentDto studentDto) {
        StudentEntity studentEntity = studentMapper.mapFrom(studentDto);

        studentEntity = studentService.updatePartial(id, studentEntity);

        return new ResponseEntity<>(studentMapper.mapTo(studentEntity), HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") String id) {
        studentService.deleteById(id);

        String message = String.format("Student with id %s deleted", id);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", message);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
