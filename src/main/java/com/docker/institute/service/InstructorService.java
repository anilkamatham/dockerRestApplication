package com.docker.institute.service;

import org.springframework.stereotype.Service;

import com.docker.institute.resource.Instructor;


public interface InstructorService {
	
public abstract Instructor getInstructor(int instructorId);
public abstract Instructor addInstructor(Instructor instructor);
public abstract Instructor updateInstructor(Instructor instructor);
public abstract Iterable<Instructor> getAllInstructors();
public abstract void deleteInstructorById(int instructorId);

}
