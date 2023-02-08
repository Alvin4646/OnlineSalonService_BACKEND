package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Appointment;

public interface IAppointmentService {
	public Appointment addAppointment(Appointment appointment);
	public String removeAppointment(long id);

	public String updateAppointment(long id, Appointment appointments);

	public Appointment getAppointmentById(Long id) ;

	public List<Appointment> getAllAppointments() ;

	public List<Appointment> getOpenAppointments() ;
}
