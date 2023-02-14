package com.salonService.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
public class Customer {
	@Id
	@GeneratedValue
	private Integer userId;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email.")
	private String email; 
	@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "pwd must be 8 chars, no special chars are allowed")
	private String password; 
	@Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 digits")
	private String contactNo;
	@Past
	@NotNull
	private LocalDate dob;
	@NotNull
	@NotBlank(message = "Address is mandatory")
	@Size(min = 3)
	private String address;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<Appointment> appointments= new ArrayList<>(); 
	@OneToOne
	private ServiceCart cart;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub 
	}
	public Customer(Integer userId, String name, String email, String password, String contactNo, LocalDate dob,
			List<Appointment> appointments, ServiceCart cart, String address) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email; 
		this.password = password;
		this.contactNo = contactNo;
		this.dob = dob;
		this.appointments = appointments;
		this.cart = cart;
		this.address = address;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public ServiceCart getCart() {
		return cart;
	}
	public void setCart(ServiceCart cart) {
		this.cart = cart;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
