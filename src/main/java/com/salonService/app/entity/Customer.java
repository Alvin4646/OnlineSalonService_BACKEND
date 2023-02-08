package com.salonService.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Customer {
	@Id
	private String userId;
	private String name;
	private String email;
	private String password;
	private Integer contactNo;
	private LocalDate dob;
	@OneToMany
	private List<Appointment> appointments= new ArrayList<>();
	@OneToOne
	private ServiceCart cart;
	@OneToOne
	private Address address;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String userId, String name, String email, String password, Integer contactNo, LocalDate dob,
			List<Appointment> appointments, ServiceCart cart, Address address) {
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	public Integer getContactNo() {
		return contactNo;
	}
	public void setContactNo(Integer contactNo) {
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
