package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.entity.ServiceCart;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.CustomerNotFoundException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.ICustomerRepository;
import com.salonService.app.repository.IServiceCartRepository;

@Service
public class ICustomerServicceImpl implements ICustomerService {
@Autowired
private ICustomerRepository iCustomerRepository ;

@Autowired
private IAppointmentService iAppointmentService;

@Autowired
private IServiceCartRepository iServiceCartRepository; 

	@Override
	public Customer getCustomer(Integer userId) throws CustomerNotFoundException {
		Optional<Customer> optCustomer = iCustomerRepository.findById(userId);	
		if(optCustomer.isPresent()) {
			return optCustomer.get();
		} 
		else{
			throw new CustomerNotFoundException("Customer not found by "+userId+ " id");
			}
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Customer customerToAdd= iCustomerRepository.save(customer);
		long id=customer.getUserId();
		ServiceCart cart=new ServiceCart(id,0D);
		iServiceCartRepository.save(cart);
		customer.setCart(cart);
		iCustomerRepository.save(customer);
		return customerToAdd;
	}

	@Override
	public Customer updateCustomer(Integer userId, Customer customer) throws CustomerNotFoundException {
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
		else {
			throw new CustomerNotFoundException("Customer not found by "+userId +" id");
		}
	}

	@Override
	public Customer deleteCustomer(Integer userId) throws CustomerNotFoundException {
		Optional<Customer> customerToBeDeleted = iCustomerRepository.findById(userId);
		if(customerToBeDeleted.isPresent()) {
			long id=userId;
			ServiceCart cart=iServiceCartRepository.findById(id).get();
			customerToBeDeleted.get().setCart(null);
	List<Appointment>list= customerToBeDeleted.get().getAppointments();
			
			customerToBeDeleted.get().getAppointments().removeAll(list);
			iCustomerRepository.save(customerToBeDeleted.get());
			iServiceCartRepository.delete(cart);
			iCustomerRepository.deleteById(userId);
			return customerToBeDeleted.get();
		}
		else {
			throw new CustomerNotFoundException("Customer not found by "+userId+" id");
		}
	}
 
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return iCustomerRepository.findAll();
	}
	
	@Override
	public List<Appointment> getAllAppointmentsForCustomer(Integer userId) throws CustomerNotFoundException{
		//return iAppointmentServiceImpl.getAppointmentById(userId);
		Customer customer=getCustomer(userId);
		if(customer==null) {
			throw new CustomerNotFoundException("No customer with this id found");
		}
		List<Appointment> list=customer.getAppointments();
		if(list.isEmpty()) {
			throw new CustomerNotFoundException("No appointments for customer found");
		}
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
