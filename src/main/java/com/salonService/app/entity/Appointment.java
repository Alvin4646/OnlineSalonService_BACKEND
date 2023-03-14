package com.salonService.app.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentId;

	@NotBlank(message = "Location cannot be empty")
	@Pattern(regexp = "Mumbai|Pune|Bangalore|Delhi|mumbai|pune|bangalore|delhi", message = "Invalid location, valid locations ara Mumbai, Pune, Bangalore, Delhi")
	private String location;

	@FutureOrPresent(message = "Invalid date! Date cannot be past date")
	@NotNull(message = "Date cannot be empty")
	private LocalDate preferredDate;

	@JsonIgnore
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	// @Pattern(regexp = "^(09|1[0-2]|1[0-9]):[0-5][0-9]:[0-5][0-9]$")
	@NotNull(message = "please enter a time it cannot be null")
	private LocalTime preferredTime;

	
	private AppointmentStatus appointmentStatus;



	@ManyToMany
	List<SalonService> serviceList = new ArrayList<>();

	@OneToOne(orphanRemoval = true)
	Payment payment;

	public Appointment(long appointmentId, String location, LocalDate preferredDate, LocalTime preferredTime,
			List<SalonService> serviceList, Payment payment, AppointmentStatus appointmentStatus) {
		super();
		this.appointmentId = appointmentId;
		this.location = location;
		this.preferredDate = preferredDate;
		this.preferredTime = preferredTime;
		this.serviceList = serviceList;
		this.payment = payment;
		this.appointmentStatus = appointmentStatus;
	}

	public Appointment() {
		super();
		
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

	public List<SalonService> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<SalonService> serviceList) {
		this.serviceList = serviceList;
	}

	public enum AppointmentStatus {
		OPEN, CLOSE, CANCELED, BOOKED,COMPLETED
	}

}
