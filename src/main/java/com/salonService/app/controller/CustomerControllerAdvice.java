package com.salonService.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.salonService.app.exception.CustomerNotFoundException;
import com.salonService.app.exception.JwtTokenMalformedException;
import com.salonService.app.exception.JwtTokenMissingException;
import com.salonService.app.exception.UserAlreadyExists;
@RestControllerAdvice
public class CustomerControllerAdvice {
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCustomerGetException(Exception e) {
		return e.getMessage();
	}
	@ExceptionHandler(UserAlreadyExists.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleUserExistException(Exception e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
	@ExceptionHandler(JwtTokenMalformedException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleJwtTokenMalformedException(Exception e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(JwtTokenMissingException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleJwtTokenMissingException(Exception e) {
		return e.getMessage();
	}
	
}
