package com.salonService.app.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentId;
	
	@NotNull(message = "Location cannot be empty")
	private String location;
	
	@NotNull(message = "Date cannot be empty")
	private LocalDate preferredDate;
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime preferredTime;
	
	@NotNull
	private AppointmentStatus appointmentStatus;

	@ManyToOne
	ServiceCart cart;
	//List<SalonService> serviceName =new ArrayList<>();
	@OneToOne( orphanRemoval = true)
	Payment payment;
	
	public Appointment(long appointmentId, String location, LocalDate preferredDate, LocalTime preferredTime,
			ServiceCart cart, Payment payment,AppointmentStatus appointmentStatus) {
		super();
		this.appointmentId = appointmentId;
		this.location = location;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.cart = cart;
		this.payment = payment;
		this.appointmentStatus=appointmentStatus;
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
	public Payment getPayment() {
		return payment;
	}
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public ServiceCart getCart() {
		return cart;
	}
	public void setCart(ServiceCart cart) {
		this.cart = cart;
	}

	public enum AppointmentStatus{
		OPEN,CLOSE
	}
	
}
