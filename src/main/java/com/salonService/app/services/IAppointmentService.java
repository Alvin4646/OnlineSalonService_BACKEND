package com.salonService.app.services;

import java.time.LocalDate;
import java.util.List;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Appointment.AppointmentStatus;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.CustomerNotFoundException;
import com.salonService.app.exception.DuplicateAppointmentException;

public interface IAppointmentService {
	public Appointment addAppointment(Appointment appointment)throws DuplicateAppointmentException;
	public String removeAppointment(long id)throws AppointmentException;

	public Appointment updateAppointment(long id, Appointment appointments)throws AppointmentException;

	public Appointment getAppointmentById(Long id) throws AppointmentException;

	public List<Appointment> getAllAppointments() throws AppointmentException;

	public List<Appointment> getOpenAppointments(AppointmentStatus status)throws AppointmentException ;
	
	public Appointment addAppointmentToCustomer(Appointment appointment,int custId)throws Exception ;
	
	public Appointment updateAppointmentDate(long id,LocalDate preferredDate)throws AppointmentException;
	
	public List<Appointment> getAppointmentByDate(LocalDate date)throws AppointmentException;
	
	public Payment removePaymenttByid(long aid) throws AppointmentException ;
	
	
	public List<SalonService> getServiceList(long aid) throws AppointmentException;
	
	public String cancelAppointment(long id) throws AppointmentException;
	
	public Appointment bookAppointment(long aid,Integer cid,Payment payment) throws AppointmentException, CustomerNotFoundException;
}
