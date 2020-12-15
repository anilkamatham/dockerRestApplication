package com.docker.institute.dao;

import org.springframework.data.repository.CrudRepository;

import com.docker.institute.resource.Instructor;

public interface InstructorDao extends CrudRepository<Instructor, Integer>{

}
