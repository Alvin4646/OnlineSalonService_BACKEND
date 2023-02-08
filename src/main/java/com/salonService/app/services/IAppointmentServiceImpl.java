package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.repository.IAppointmentRepository;
@Service
public class IAppointmentServiceImpl implements IAppointmentService {
	@Autowired
	private IAppointmentRepository iappointmentRepo;

	@Override
	public Appointment addAppointment(Appointment appointment) {
	
		return iappointmentRepo.save(appointment);
	}

	@Override
	public String removeAppointment(long id) {
		iappointmentRepo.deleteById(id);
		return "Appointment Deleted";
		
	}

	@Override
	public String updateAppointment(long id, Appointment appointments) {
		Optional<Appointment>existingAppointment=iappointmentRepo.findById(id);
		if(!existingAppointment.isPresent()) {
			return "Id not found";
		}
		Appointment updatedAppointment=existingAppointment.get();
		updatedAppointment.setAppointmentId(appointments.getAppointmentId());
		updatedAppointment.setLocation(appointments.getLocation());
		updatedAppointment.setPreferredDate(appointments.getPreferredDate());
		updatedAppointment.setPreferredTime(appointments.getPreferredTime());
		updatedAppointment.setServiceName(appointments.getServiceName());
		updatedAppointment.setPayment(appointments.getPayment());
		iappointmentRepo.save(updatedAppointment);
		return "Updated Succesfully";
		
	}

	@Override
	public Appointment getAppointmentById(Long id) {
		Optional<Appointment> foundAppointment= iappointmentRepo.findById(id);
		if(!foundAppointment.isPresent()) {
			//Exception
		}
		return foundAppointment.get();
	}

	@Override
	public List<Appointment> getAllAppointments() {
		// TODO Auto-generated method stub
		return iappointmentRepo.findAll();
	}

	@Override
	public List<Appointment> getOpenAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

}
