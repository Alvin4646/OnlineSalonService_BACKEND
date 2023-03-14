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
import com.salonService.app.entity.Appointment.AppointmentStatus;
import com.salonService.app.entity.Customer;
import com.salonService.app.entity.Payment;
import com.salonService.app.entity.Payment.PaymentStatus;
import com.salonService.app.entity.SalonService;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.CustomerNotFoundException;
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

		return iappointmentRepo.save(appointment); 

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
		Optional<Appointment> appointment = iappointmentRepo.findById(id);
		if (!appointment.isPresent()) {
			throw new AppointmentException("Unable to delete no appointment with id " + id + " found");
		}
		iappointmentRepo.deleteById(id);

		return "Appointment Deleted";

	}
	
	/************************************************************************************
	 * Method: 					- updateAppointment
     *Description: 				- To update an existing appointment 
	 * @param id       			- id of the appointment you want to update 
	 * @param appointment		- appointment entity with changes
	 * @returns Appointment     - the updated appointment is returned
	 * @throws AppointmentException - It is raised when no appointment with the provided id is found
     *Created By                - Albin Anil Pallipeedika
     *Created Date              - 9-FEB-2023                           
	 ************************************************************************************/

	@Override
	public Appointment updateAppointment(long id, Appointment appointments) throws AppointmentException {
		Optional<Appointment> existingAppointment = iappointmentRepo.findById(id);
		if (!existingAppointment.isPresent()) {
			throw new AppointmentException("Could not update!! No appointment with id " + id + " found");
		}
		Appointment updatedAppointment = existingAppointment.get();

		updatedAppointment.setLocation(appointments.getLocation());
		updatedAppointment.setPreferredDate(appointments.getPreferredDate());
		updatedAppointment.setPreferredTime(appointments.getPreferredTime());


		return iappointmentRepo.save(updatedAppointment);
	}
	
	/************************************************************************************
	 * Method: 					- updateAppointmentDate
     *Description: 				- To update date of an existing appointment 
	 * @param id       			- id of the appointment you want to update 
	 * @param preferredDate		- the date that is to be added
	 * @returns Appointment     - the updated appointment is returned
	 * @throws AppointmentException - It is raised when no appointment with the provided id is found
     *Created By                - Albin Anil Pallipeedika
     *Created Date              - 9-FEB-2023                           
	 ************************************************************************************/

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
	
	/************************************************************************************
	 * Method: 					- getAppointmentById
     *Description: 				- To get appointment by the id 
	 * @param id       			- id of the appointment you want to get 
	 * @returns Appointment     - the appointment is returned
	 * @throws AppointmentException - It is raised when no appointment with the provided id is found
     *Created By                - Albin Anil Pallipeedika
     *Created Date              - 9-FEB-2023                           
	 ************************************************************************************/

	@Override
	public Appointment getAppointmentById(Long id) throws AppointmentException {
		Optional<Appointment> foundAppointment = iappointmentRepo.findById(id);
		if (!foundAppointment.isPresent()) {
			throw new AppointmentException("No Appointment with this id " + id + " found");
		}
		return foundAppointment.get();
	} 
	
	/************************************************************************************
	 * Method: 					 - getAppointmentByDate
     *Description: 				 - To get all appointments by the date 
	 * @param date       		 - date of the appointment you want to get 
	 * @returns List<Appointment> - the list appointment is returned
	 * @throws AppointmentException - It is raised when no appointment with the provided id is found
     *Created By                 - Albin Anil Pallipeedika
     *Created Date               - 10-FEB-2023                           
	 ************************************************************************************/

	@Override
	public List<Appointment> getAppointmentByDate(LocalDate date) throws AppointmentException {
		
		List<Appointment> findAppointment = iappointmentRepo.findByPreferredDate(date);
		if (!findAppointment.isEmpty()) {
			return iappointmentRepo.findByPreferredDate(date);
		}
		throw new AppointmentException("No appointment with this date " + date + " found");
	}
	
	/************************************************************************************
	 * Method: 					 - getAllAppointments
     *Description: 				 - To get all appointments by the date  
	 * @returns List<Appointment> - the list appointment is returned
	 * @throws AppointmentException - It is raised when no appointment are found
     *Created By                 - Albin Anil Pallipeedika
     *Created Date               - 10-FEB-2023                           
	 ************************************************************************************/

	@Override 
	public List<Appointment> getAllAppointments()throws AppointmentException {
		List<Appointment> foundAppointments=iappointmentRepo.findAll();
		if(!foundAppointments.isEmpty()) {
		return iappointmentRepo.findAll();
		}
		throw new AppointmentException("No appointments found!");
	}
	
	/************************************************************************************
	 * Method: 					 - getOpenAppointments
     *Description: 				 - To get all appointments with status as open
	 * @returns List<Appointment> - the list appointments is returned
	 * @throws AppointmentException - It is raised when no appointment are found
     *Created By                 - Albin Anil Pallipeedika
     *Created Date               - 10-FEB-2023                           
	 ************************************************************************************/

	@Override
	public List<Appointment> getOpenAppointments(AppointmentStatus status)throws AppointmentException {
		List<Appointment> foundAppointments=iappointmentRepo.findByAppointmentStatus(status);
		if(!foundAppointments.isEmpty()) {
		return foundAppointments;
		}
		throw new AppointmentException("No appointments found !");
	}
	
	/************************************************************************************
	 * Method: 					 - addAppointmentToCustomer
     *Description: 				 - To add Appointment to a particular customer
	 * @param appointment        - the appointment you want to add
	 * @param custId			 - the id of customer who is booking appointment
	 * @returns Appointment		 - the added appointment is returned
	 * @throws AppointmentException - It is raised when no appointment are found
     *Created By                 - Albin Anil Pallipeedika
     *Created Date               - 10-FEB-2023                           
	 ************************************************************************************/

	@Override
	public Appointment addAppointmentToCustomer(Appointment appointment, int custId) throws Exception {
		Optional<Customer> customer = iCustomerRepo.findById(custId);
		if (customer.isEmpty()) {
			throw new AppointmentException("No customer with this id " + custId + " found");
		}
		Customer foundCustomer = customer.get();
		List<Appointment> allAppointments=iappointmentRepo.findAll();
		for(Appointment existingAppointment:allAppointments) {
			if(existingAppointment.getLocation().equals(appointment.getLocation())
					&& existingAppointment.getPreferredDate().equals(appointment.getPreferredDate())
					&&existingAppointment.getPreferredTime().equals(appointment.getPreferredTime())&& existingAppointment.getAppointmentStatus()!=AppointmentStatus.CANCELED) {
				throw new AppointmentException("An appointment on this slot already exists");
			}
		}
		appointment.setAppointmentStatus(AppointmentStatus.OPEN);
		Appointment newAppointment = addAppointment(appointment);
		newAppointment.getServiceList ().addAll(foundCustomer.getCart().getServiceList());
	
		foundCustomer.getAppointments().add(newAppointment);
		iCustomerRepo.save(foundCustomer);
		return newAppointment; 
		
	}
	
	/************************************************************************************
	 * Method: 					 - removePaymenttByid
     *Description: 				 - To remove Payment from a particular appointment
     * @param aid 				 - id of payment to be removed
	 * @returns Payment			 - the removed payment is returned
	 * @throws AppointmentException - It is raised when no appointment are found
     *Created By                 - Albin Anil Pallipeedika
     *Created Date               - 10-FEB-2023                           
	 ************************************************************************************/

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
	

	
	/************************************************************************************
	 * Method: 					 - getServiceList
     *Description: 				 - To remove appointment from a particular customer
     * @param aid 				 - the appointment from whom you want to fetch service list
	 * @returns List<SalonService> - the removed appointment is returned
	 * @throws AppointmentException - It ims raised when no appointment are found or when the service list is empty
     *Created By                 - Albin Anil Pallipeedika
     *Created Date               - 10-FEB-2023                           
	 ************************************************************************************/
	
	@Override
	public List<SalonService> getServiceList(long aid) throws AppointmentException{
		Appointment appointment=getAppointmentById(aid);
		
		return appointment.getServiceList();
	}
	
	public String cancelAppointment(long id) throws AppointmentException {
		Appointment foundAppointment=getAppointmentById(id);
		if(foundAppointment.getAppointmentStatus()==AppointmentStatus.CANCELED)
			return "Appointment is already Canceled";
		foundAppointment.setAppointmentStatus(AppointmentStatus.CANCELED);
		if(foundAppointment.getPayment().getStatus()==PaymentStatus.PAID) {
			foundAppointment.getPayment().setStatus(PaymentStatus.REFUNDED);
		}
		iappointmentRepo.save(foundAppointment);
		return "Appointment Canceled successfully";
	}
	
	public Appointment bookAppointment(long aid,Integer cid,Payment payment) throws AppointmentException, CustomerNotFoundException {
		Appointment foundAppointment=getAppointmentById(aid);
		Optional<Customer> cust=iCustomerRepo.findById(cid);
		if(!cust.isPresent())
			throw new CustomerNotFoundException("No Customer with this id found");
		Customer foundCustomer=cust.get();
		ServiceCart cart=foundCustomer.getCart();
		cart.getServiceList().clear();
		
		foundAppointment.setAppointmentStatus(AppointmentStatus.BOOKED);
		payment.setAmount(foundCustomer.getCart().getAmount());
		foundCustomer.getCart().setAmount(0.0);
		foundAppointment.setPayment(payment);
		paymentRepo.save(payment);
		iappointmentRepo.save(foundAppointment);
		iCustomerRepo.save(foundCustomer);
	
		
		return foundAppointment;
	}
	
	public String completeAppointment(long aid) throws AppointmentException {
		Appointment foundAppointment=getAppointmentById(aid);
		if(foundAppointment.getAppointmentStatus()==AppointmentStatus.COMPLETED)
			return "Appointment is already Completed";
		foundAppointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
		iappointmentRepo.save(foundAppointment);
		return "Appointment Confirmed Successfully";
	}
	
}
