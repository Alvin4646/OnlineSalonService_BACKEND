package com.salonService.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.services.ICustomerService;

@RestController
public class CustomerController {
	@Autowired
	private ICustomerService iCustomerService;

	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return this.iCustomerService.addCustomer(customer);
	}

	@GetMapping("/customer/{aid}")
	public ResponseEntity<?> getCustomer(@PathVariable("aid") Integer custId, HttpServletRequest request) {

		Customer customer = iCustomerService.getCustomer(custId); 
		if (customer == null) {
			// Exception
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	@DeleteMapping("/deleteCustomer/{cid}")
	public ResponseEntity<String> removeCustomer(@PathVariable("aid") Integer custId, HttpServletRequest request) {
		Customer deleteCustomer = iCustomerService.deleteCustomer(custId) ;
		if(deleteCustomer != null) { 
			return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failed to delete customer", HttpStatus.BAD_REQUEST);
	}

//	@DeleteMapping("/deleteCustomer/(id)")
//	public void deleteCustomer(@PathVariable Integer id) {
//		iCustomerService.deleteCustomer(id);
//	}

//@GetMapping("/customerById/(id)")
//public Customer findCustomerById(@PathVariable("id") Integer id) {
//	return iCustomerService.getCustomer(id);
//}
	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		return iCustomerService.getAllCustomers();
	}

	@PutMapping("/updateCustomers/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
		return iCustomerService.updateCustomer(id, customer);
	}
//	@PutMapping("/updateCustomers/(id)")
//	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id, HttpServletRequest request) {
//
//		Customer updatedCustomer = iCustomerService.updateCustomer(id, customer);
//		if(updatedCustomer != null) {
//			return new ResponseEntity<String>("Customer updated successfully", HttpStatus.OK);
//		}
//		else
//			return new ResponseEntity<String>("Customer failed to update", HttpStatus.NOT_FOUND);
//	}
	
	@GetMapping("/customerAppointments/{id}")
	public List<Appointment> getAllCustomerAppointments(@PathVariable Integer id){
		return iCustomerService.getAllAppointmentsForCustomer(id);
	}
	@DeleteMapping("/customer/{cid}")
	public Appointment removeAppointment(@RequestBody long aid,@PathVariable Integer cid) throws AppointmentException{
		return iCustomerService.removeAppointmentByid(cid, aid);
	}
}
