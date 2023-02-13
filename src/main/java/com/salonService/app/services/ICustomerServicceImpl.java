package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ICustomerRepository;

@Service
public class ICustomerServicceImpl implements ICustomerService {
@Autowired
private ICustomerRepository iCustomerRepository ;

@Autowired
private IAppointmentService iAppointmentService;

	@Override
	public Customer getCustomer(Integer userId) {
		Optional<Customer> optCustomer = iCustomerRepository.findById(userId);	
		if(optCustomer.isPresent()) {
			return optCustomer.get();
		}
		else 
		return null;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return iCustomerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Integer userId, Customer customer) {
		if(iCustomerRepository.existsById(userId)) {
			Customer customerToBeUpdated = iCustomerRepository.findById(userId).get();
			customerToBeUpdated.setAddress(customer.getAddress());
			customerToBeUpdated.setContactNo(customer.getContactNo());
			customerToBeUpdated.setDob(customer.getDob());
			customerToBeUpdated.setEmail(customer.getEmail());
			customerToBeUpdated.setName(customer.getName());
			iCustomerRepository.save(customerToBeUpdated);
			return customerToBeUpdated;
		}
		return null;
	}

	@Override
	public Customer deleteCustomer(Integer userId) {
		Optional<Customer> customerToBeDeleted = iCustomerRepository.findById(userId);
		if(customerToBeDeleted.isPresent()) {
			iCustomerRepository.deleteById(userId);
			return customerToBeDeleted.get();
		}
		else {
		return null;
		}
	}
 
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return iCustomerRepository.findAll();
	}
	
	@Override
	public List<Appointment> getAllAppointmentsForCustomer(Integer userId){
		//return iAppointmentServiceImpl.getAppointmentById(userId);
		Customer customer=getCustomer(userId);
		
		return customer.getAppointments();
	}
	
	public Appointment removeAppointmentByid(Integer cid,long aid) throws AppointmentException {
		Customer cust=iCustomerRepository.findById(cid).get();
		Appointment appointmentToRemove=null;
		for(Appointment appointment:cust.getAppointments()) {
			if(appointment.getAppointmentId()==aid) {
				appointmentToRemove=appointment;
				break;
			}
			
		}
		if(appointmentToRemove!=null) { 
			cust.getAppointments().remove(appointmentToRemove);
			iAppointmentService.removeAppointment(aid);
			iCustomerRepository.save(cust);
		}
		return appointmentToRemove;
	}
}
