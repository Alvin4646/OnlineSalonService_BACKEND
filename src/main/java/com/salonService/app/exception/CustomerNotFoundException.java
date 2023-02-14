package com.salonService.app.exception;

public class CustomerNotFoundException extends Exception{
  public CustomerNotFoundException(String msg)
  {
	  super(msg);
  }
}
