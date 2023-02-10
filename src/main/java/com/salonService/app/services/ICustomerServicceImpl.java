package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.repository.ICustomerRepository;
@Service
public class ICustomerServicceImpl implements ICustomerService {
@Autowired
private ICustomerRepository iCustomerRepository ;
@Autowired
private IAppointmentServiceImpl iAppointmentServiceImpl;

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
			iCustomerRepository.save(customer);
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

}
