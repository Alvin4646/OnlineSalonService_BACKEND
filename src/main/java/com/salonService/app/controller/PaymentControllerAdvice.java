package com.salonService.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.salonService.app.exception.PaymentException;

@RestControllerAdvice
public class PaymentControllerAdvice {
	@ExceptionHandler(PaymentException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleAppointmentException(Exception e) {
		return e.getMessage();
	}
}
