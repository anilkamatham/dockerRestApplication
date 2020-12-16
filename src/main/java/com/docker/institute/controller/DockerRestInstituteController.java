package com.docker.institute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docker.institute.resource.Instructor;
import com.docker.institute.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class DockerRestInstituteController {
	@Autowired	
    private InstructorService instructorService;
	
	@GetMapping("/{instructorId}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable("instructorId") int instructorId){
		Instructor instructor = instructorService.getInstructor(instructorId);
		return new ResponseEntity<>(instructor, HttpStatus.OK); 
	}
	
	@PostMapping
	public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor instructor){
		return new ResponseEntity<>(instructorService.addInstructor(instructor),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Instructor> updateInstructor(@RequestBody Instructor instructor){
		return new ResponseEntity<Instructor>(instructorService.updateInstructor(instructor),HttpStatus.OK);
	}
	
	@DeleteMapping("{instructorId}")
	public ResponseEntity<String> deleteInstructorById(@PathVariable("instructorId") int instructorId){
		instructorService.deleteInstructorById(instructorId);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Instructor>> getAllInstructors(){
	   return new ResponseEntity<>(instructorService.getAllInstructors(), HttpStatus.OK);	
	}
		
}
