package com.salonService.app.services;
/************************************************************************************
 *          @author          Albin Anil Pallipeedika
 *          Description      It's a service class to add appointments to customer, remove appointments
           					 update it and to get appointments
 *         Version           1.0
 *         Created Date      08-FEB-2023
 ************************************************************************************/

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.DuplicateAppointmentException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ICustomerRepository;
import com.salonService.app.repository.IPaymentRepository;

@Service
public class IAppointmentServiceImpl implements IAppointmentService {
	@Autowired
	private IAppointmentRepository iappointmentRepo;
	@Autowired
	private ICustomerRepository iCustomerRepo;
	@Autowired
	private IPaymentRepository paymentRepo;
	
	/************************************************************************************
	 * Method: 					- addAppointment
     *Description: 				- To add appointment internally
	 * @param appointment       - appointment to be added
	 * @returns appointment     - the appointment added is returned
     *Created By                - Albin Anil Pallipeedika
     *Created Date              - 8-FEB-2023                           
	 ************************************************************************************/

	@Override
	public Appointment addAppointment(Appointment appointment) throws DuplicateAppointmentException {

		Appointment newAppointment = iappointmentRepo.save(appointment);
		return newAppointment; 

	}
	
	/************************************************************************************
	 * Method: 					- removeAppointment
     *Description: 				- To remove an appointment 
	 * @param id       			- id of the appointment you want to remove
	 * @returns String     - the appointment added is returned
	 * @throws AppointmentException - It is raised when no appointment with the provided id is found
     *Created By                - Albin Anil Pallipeedika
     *Created Date              - 8-FEB-2023                           
	 ************************************************************************************/
	
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
		// updatedAppointment.setAppointmentId(appointments.getAppointmentId());
		updatedAppointment.setLocation(appointments.getLocation());
		updatedAppointment.setPreferredDate(appointments.getPreferredDate());
		updatedAppointment.setPreferredTime(appointments.getPreferredTime());
		updatedAppointment.setAppointmentStatus(appointments.getAppointmentStatus());
		// updatedAppointment.setCart(appointments.getCart());
		// updatedAppointment.setPayment(appointments.getPayment());

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
	public List<Appointment> getAppointmentByDate(LocalDate date) throws AppointmentException {
		// TODO Auto-generated method stub
		List<Appointment> findAppointment = iappointmentRepo.findByPreferredDate(date);
		if (!findAppointment.isEmpty()) {
			return iappointmentRepo.findByPreferredDate(date);
		}
		throw new AppointmentException("No appointment with this date " + date + " found");
	}

	@Override 
	public List<Appointment> getAllAppointments()throws AppointmentException {
		List<Appointment> foundAppointments=iappointmentRepo.findAll();
		if(!foundAppointments.isEmpty()) {
		return iappointmentRepo.findAll();
		}
		throw new AppointmentException("No appointments found!");
	}

	@Override
	public List<Appointment> getOpenAppointments()throws AppointmentException {
		List<Appointment> foundAppointments=iappointmentRepo.findByAppointmentStatus(Appointment.AppointmentStatus.OPEN);
		if(!foundAppointments.isEmpty()) {
		return foundAppointments;
		}
		throw new AppointmentException("No open appointments found !");
	}

	@Override
	public Appointment addAppointmentToCustomer(Appointment appointment, int custId) throws Exception {
		Optional<Customer> customer = iCustomerRepo.findById(custId);
		if (customer.isEmpty()) {
			throw new AppointmentException("No customer with this id " + custId + " found");
		}
		Customer foundCustomer = customer.get();
		Appointment newAppointment = addAppointment(appointment);
		newAppointment.setCart(foundCustomer.getCart());
		foundCustomer.getAppointments().add(newAppointment);
		iCustomerRepo.save(foundCustomer);
		return newAppointment; 
	}

	@Override
	public Payment removePaymenttByid(long aid) throws AppointmentException {
		Appointment app = getAppointmentById(aid); 
		Payment pay = app.getPayment(); 
		if (pay != null) {  
			app.setPayment(null); 
			paymentRepo.delete(pay); 
			iappointmentRepo.save(app); 
			return pay; 
		}
		throw new AppointmentException("No payment found for this appointment with id" + aid); 
	}
	@Override
	public Appointment removeAppointmentByid(Integer cid,long aid) throws AppointmentException {
		Customer cust=iCustomerRepo.findById(cid).get();
		Appointment appointmentToRemove=null;
		for(Appointment appointment:cust.getAppointments()) {
			if(appointment.getAppointmentId()==aid) {
				appointmentToRemove=appointment;
				break;
			}
		}
		if(appointmentToRemove!=null) { 
			cust.getAppointments().remove(appointmentToRemove);
			removeAppointment(aid);
			iCustomerRepo.save(cust);
		}
		return appointmentToRemove;
	}
	
	@Override
	public List<SalonService> getServiceList(long aid) throws AppointmentException{
		Appointment appointment=getAppointmentById(aid);
		ServiceCart  cart=appointment.getCart();
		if(cart==null) { 
			throw new AppointmentException("No cart found for appointment");
		}
		List<SalonService> service=cart.getServiceList();
		if(service.isEmpty()) {
			throw new AppointmentException("No services found");
		}
		return service;
	}
}
