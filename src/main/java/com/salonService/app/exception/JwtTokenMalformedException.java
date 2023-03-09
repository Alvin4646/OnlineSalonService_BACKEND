package com.salonService.app.exception;

public class JwtTokenMalformedException extends Exception{
	public JwtTokenMalformedException(String msg){
		super(msg);
	}

}
