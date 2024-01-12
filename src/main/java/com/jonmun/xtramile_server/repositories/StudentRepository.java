package com.jonmun.xtramile_server.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonmun.xtramile_server.domain.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, String> {

}
