package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.exception.AppointmentException;

public interface ICustomerService {
	public Customer getCustomer(Integer userId) ;

	public Customer addCustomer(Customer customer) ;

	public Customer updateCustomer(Integer userId, Customer customer) ;

	public Customer deleteCustomer(Integer userId) ;
	
	public Appointment removeAppointmentByid(Integer id,long aid)throws AppointmentException;

	public List<Customer> getAllCustomers() ;
	
	public List<Appointment> getAllAppointmentsForCustomer(Integer userId);
}
