package com.salonService.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.salonService.app.exception.SalonServiceNotFoundException;

@RestControllerAdvice
public class SalonServiceControllerAdvice {

	@ExceptionHandler(SalonServiceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String SalonServiceNotFoundException(SalonServiceNotFoundException e) {
		return e.getMessage();
	}

	@ExceptionHandler(com.salonService.app.exception.ServiceAlreadyExistsException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String ServiceAlreadyExistsException(com.salonService.app.exception.ServiceAlreadyExistsException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
