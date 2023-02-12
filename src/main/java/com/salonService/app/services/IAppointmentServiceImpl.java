package com.salonService.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.DuplicateAppointmentException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ICustomerRepository;

@Service
public class IAppointmentServiceImpl implements IAppointmentService {
	@Autowired
	private IAppointmentRepository iappointmentRepo;
	@Autowired
	private ICustomerRepository iCustomerRepo;

	@Override
	public Appointment addAppointment(Appointment appointment) throws DuplicateAppointmentException {
		
		
			Appointment newAppointment = iappointmentRepo.save(appointment);
			return newAppointment;
		
	}

	@Override
	public String removeAppointment(long id) throws AppointmentException {
		// iappointmentRepo.deleteById(id);
		Optional<Appointment> appointment = iappointmentRepo.findById(id); 
		if (!appointment.isPresent()) {
			throw new AppointmentException("Unable to delete no appointment with id " + id + " found");
		}
		iappointmentRepo.deleteById(id);

		return "Appointment Deleted";

	}

	@Override
	public Appointment updateAppointment(long id, Appointment appointments) throws AppointmentException {
		Optional<Appointment> existingAppointment = iappointmentRepo.findById(id);
		if (!existingAppointment.isPresent()) {
			throw new AppointmentException("Could not update!! No appointment with id " + id + " found");
		}
		Appointment updatedAppointment = existingAppointment.get();
		updatedAppointment.setAppointmentId(appointments.getAppointmentId());
		updatedAppointment.setLocation(appointments.getLocation());
		updatedAppointment.setPreferredDate(appointments.getPreferredDate());
		updatedAppointment.setPreferredTime(appointments.getPreferredTime());
		updatedAppointment.setCart(appointments.getCart());
		updatedAppointment.setPayment(appointments.getPayment());
		
		return iappointmentRepo.save(updatedAppointment);
	}

	@Override
	public Appointment updateAppointmentDate(long id, LocalDate preferredDate) throws AppointmentException {
		Optional<Appointment> existingAppointment = iappointmentRepo.findById(id);
		if (!existingAppointment.isPresent()) {
			throw new AppointmentException("Could not update date no appointment with id " + id + " found");
		}
		Appointment updatedAppointment = existingAppointment.get();
		updatedAppointment.setPreferredDate(preferredDate);
		iappointmentRepo.save(updatedAppointment);
		return updatedAppointment;

	}

	@Override
	public Appointment getAppointmentById(Long id) throws AppointmentException {
		Optional<Appointment> foundAppointment = iappointmentRepo.findById(id);
		if (!foundAppointment.isPresent()) {
			throw new AppointmentException("No Appointment with this id " + id + " found");
		}
		return foundAppointment.get();
	}

	@Override
	public List<Appointment> getAppointmentByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return iappointmentRepo.findByPreferredDate(date);
	}

	@Override
	public List<Appointment> getAllAppointments() {

		return iappointmentRepo.findAll();
	}

	@Override
	public List<Appointment> getOpenAppointments() {
		return iappointmentRepo.findByAppointmentStatus(Appointment.AppointmentStatus.OPEN);
	}

	@Override
	public Appointment addAppointmentToCustomer(Appointment appointment, int custId) throws Exception {
		Optional<Customer> customer = iCustomerRepo.findById(custId);
		if (customer.isEmpty()) {
			throw new AppointmentException("No customer with this id " + custId + " found");
		}
		Customer foundCustomer = customer.get();
		Appointment newAppointment = addAppointment(appointment);
		foundCustomer.getAppointments().add(newAppointment);
		iCustomerRepo.save(foundCustomer);
		return newAppointment;
	}

}
