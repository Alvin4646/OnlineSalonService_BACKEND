package com.salonService.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Entity
public class Admin {
	@Id
	@NotNull
	private String adminId;
	
	private String adminPassword;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String userId,String adminPassword) {
		super();
		this.adminId = userId;
		this.adminPassword=adminPassword;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String userId) {
		this.adminId = userId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPassword=" + adminPassword + "]";
	}



}
