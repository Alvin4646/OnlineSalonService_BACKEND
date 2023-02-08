package com.salonService.app.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
@Entity
public class Appointment {
	@Id
	private long appointmentId;
	private String location;
	private LocalDate preferredDate;
	private LocalTime preferredTime;
	@ManyToMany
	List<SalonService> serviceName =new ArrayList<>();
	@OneToOne
	Payment payment;
	public Appointment(long appointmentId, String location, LocalDate preferredDate, LocalTime preferredTime,
			List<SalonService> serviceName, Payment payment) {
		super();
		this.appointmentId = appointmentId;
		this.location = location;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.serviceName = serviceName;
		this.payment = payment;
	}
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(LocalDate preferredDate) {
		this.preferredDate = preferredDate;
	}
	public LocalTime getPreferredTime() {
		return preferredTime;
	}
	public void setPreferredTime(LocalTime preferredTime) {
		this.preferredTime = preferredTime;
	}
	public List<SalonService> getServiceName() {
		return serviceName;
	}
	public void setServiceName(List<SalonService> serviceName) {
		this.serviceName = serviceName;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}
