package com.docker.institute.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ErrorMessage errorMessage;
	
	@ExceptionHandler(value = InstructorNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleInstructorNotFoundException(InstructorNotFoundException ex){
		errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
		errorMessage.setTimestamp(new Date());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
