package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;

public interface ICustomerService {
	public Customer getCustomer(Integer userId) ;

	public Customer addCustomer(Customer customer) ;

	public Customer updateCustomer(Integer userId, Customer customer) ;

	public Customer deleteCustomer(Integer userId) ;

	public List<Customer> getAllCustomers() ;
	
	public List<Appointment> getAllAppointmentsForCustomer(Integer userId);
}
