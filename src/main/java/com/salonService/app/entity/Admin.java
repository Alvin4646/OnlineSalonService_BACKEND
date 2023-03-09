package com.salonService.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
@Entity
public class Admin {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Min(value = 1,message = "id value must be > 0")
	Long id;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}$",message = "pwd must Minimum 6 and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	String password;
	public Admin() {
		super();
	}
	public Admin(Long id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
