package com.jonmun.xtramile_server.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jonmun.xtramile_server.domain.dto.CreateStudentDto;
import com.jonmun.xtramile_server.domain.entities.StudentEntity;
import com.jonmun.xtramile_server.mappers.Mapper;

@Component
public class StudentMapperImpl implements Mapper<StudentEntity, CreateStudentDto> {

    private ModelMapper modelMapper;

    public StudentMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateStudentDto mapTo(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, CreateStudentDto.class);
    }

    @Override
    public StudentEntity mapFrom(CreateStudentDto studentDto) {
        return modelMapper.map(studentDto, StudentEntity.class);
    }

}
