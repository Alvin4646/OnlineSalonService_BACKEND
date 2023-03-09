package com.salonService.app.exception;

public class InvalidUserException extends RuntimeException {
	
	public InvalidUserException(String message) {
		super(message);
	}

}