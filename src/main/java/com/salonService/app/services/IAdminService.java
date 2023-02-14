package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Admin;
import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.exception.SalonServiceNotFoundException;
import com.salonService.app.exception.ServiceAlreadyExistsException;
import com.salonService.app.exception.UserAlreadyExists;
import com.salonService.app.exception.UserNotExistsException;

public interface IAdminService {
	public Admin signUp(Admin user) throws UserNotExistsException, UserAlreadyExists;

	public Admin signIn(Admin user) throws UserNotExistsException;

	public String changePassword(Admin user) throws UserNotExistsException;
//	public SalonService addService(SalonService salonService) throws ServiceAlreadyExistsException ;
//	public void removeService(Long serviceId) throws SalonServiceNotFoundException ;
//	public SalonService updateService(Long serviceId, SalonService salonService) throws SalonServiceNotFoundException ;
//	public List<Appointment> getAllAppointments() ;
//	public Appointment getAppointmentById(Long id) ;
}
