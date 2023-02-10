package com.salonService.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.DuplicateAppointmentException;

@RestControllerAdvice
public class AppointmentControllerAdvice {
	@ExceptionHandler(AppointmentException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleAppointmentException(Exception e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(DuplicateAppointmentException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleDuplicateAppointmentException(Exception e) {
		return e.getMessage();
	}

}
