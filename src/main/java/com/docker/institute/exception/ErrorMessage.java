package com.docker.institute.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {

	private int status;
	private Date timestamp;
	private String errorMessage;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
