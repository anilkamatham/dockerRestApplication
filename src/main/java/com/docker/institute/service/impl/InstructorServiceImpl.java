package com.docker.institute.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docker.institute.dao.InstructorDao;
import com.docker.institute.exception.InstructorNotFoundException;
import com.docker.institute.resource.Instructor;
import com.docker.institute.service.InstructorService;

@Service
public class InstructorServiceImpl implements InstructorService{
	@Autowired
    private InstructorDao instructorDao;
    
	@Override
	public Instructor getInstructor(int instructorId) {
		return instructorDao.findById(instructorId).orElseThrow(() -> new InstructorNotFoundException("Instructor does not exists")); 
	}

	@Override
	public Instructor addInstructor(Instructor instructor) {
		return instructorDao.save(instructor);
	}

	@Override
	public Instructor updateInstructor(Instructor instructor) {
		Instructor currentInstructor = instructorDao.findById(instructor.getId()).orElseThrow(() -> new InstructorNotFoundException("Instructor does not exits"));
		if(currentInstructor != null) {
	       instructor.setCourses(currentInstructor.getCourses());	       
		}
		return instructorDao.save(instructor);
	}

	@Override
	public Iterable<Instructor> getAllInstructors() {
		return instructorDao.findAll();
	}

	@Override
	public void deleteInstructorById(int instructorId) {
		if(instructorDao.existsById(instructorId)) {
			instructorDao.deleteById(instructorId);
		}
		else {
			throw new InstructorNotFoundException("Instructor does not exists");
		}
		
	}

}
