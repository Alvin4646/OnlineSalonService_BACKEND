package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.CustomerNotFoundException;

public interface ICustomerService {
	public Customer getCustomer(Integer userId) throws CustomerNotFoundException ;

	public Customer addCustomer(Customer customer) ;

	public Customer updateCustomer(Integer userId, Customer customer) throws CustomerNotFoundException ;

	public Customer deleteCustomer(Integer userId) throws CustomerNotFoundException ;
	
	public Appointment removeAppointmentByid(Integer id,long aid)throws AppointmentException;

	public List<Customer> getAllCustomers() ;
	
	public List<Appointment> getAllAppointmentsForCustomer(Integer userId) throws CustomerNotFoundException;
}
