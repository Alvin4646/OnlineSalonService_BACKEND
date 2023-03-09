package com.salonService.app.entity;

public class LoginCredentials {
	
	private String userName;
	private String password;
	
	public LoginCredentials() {
		
	}

	public String getUsername() {
		return this.userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}