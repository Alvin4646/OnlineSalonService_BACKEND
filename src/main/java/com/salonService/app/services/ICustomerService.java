package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.CustomerNotFoundException;
import com.salonService.app.exception.UserAlreadyExists;

public interface ICustomerService {
	public Customer getCustomer(Integer userId) throws CustomerNotFoundException ;

	public Customer addCustomer(Customer customer) throws UserAlreadyExists ;

	public Customer updateCustomer(Integer userId, Customer customer) throws CustomerNotFoundException ;

	public Customer deleteCustomer(Integer userId) throws CustomerNotFoundException ;
	
	public String removeAppointmentByid(Integer id,long aid)throws AppointmentException;

	public List<Customer> getAllCustomers() ;
	
	public List<Appointment> getAllAppointmentsForCustomer(Integer userId) throws CustomerNotFoundException;
}
