package com.salonService.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.salonService.app.exception.CustomerNotFoundException;
import com.salonService.app.exception.JwtTokenMalformedException;
import com.salonService.app.exception.JwtTokenMissingException;
import com.salonService.app.exception.UserAlreadyExists;
import com.salonService.app.services.ICustomerService;
import com.salonService.app.util.JWTUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	@Autowired
	private ICustomerService iCustomerService;

	@PostMapping("/customer")
	public Customer addCustomer(@Valid @RequestBody Customer customer) throws UserAlreadyExists { 
		
		return this.iCustomerService.addCustomer(customer); 
	}
 
	@GetMapping("/customer/{aid}") 
	public ResponseEntity<?> getCustomer(@PathVariable("aid") Integer custId, HttpServletRequest request) throws CustomerNotFoundException {

		Customer customer = iCustomerService.getCustomer(custId); 
		if (customer == null) {
			// Exception
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping("/customer/delete/{cid}")
	public ResponseEntity<String> removeCustomer(@PathVariable("cid") Integer custId, HttpServletRequest request) throws CustomerNotFoundException {
		Customer deleteCustomer = iCustomerService.deleteCustomer(custId) ;
		if(deleteCustomer != null) {
			return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failed to delete customer", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/customer")
	public List<Customer> getAllCustomers(HttpServletRequest request) throws JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return iCustomerService.getAllCustomers();
	}

	@PutMapping("/customers/update/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) throws CustomerNotFoundException {
		return iCustomerService.updateCustomer(id, customer);
	}

	@GetMapping("/customer/appointments/{id}")
	public List<Appointment> getAllCustomerAppointments(@PathVariable Integer id) throws CustomerNotFoundException{
		return iCustomerService.getAllAppointmentsForCustomer(id);
	}
	@DeleteMapping("/customer/appointment/{cid}")
	public String removeAppointment(@RequestBody long aid,@PathVariable Integer cid) throws AppointmentException{
		return iCustomerService.removeAppointmentByid(cid, aid);
	}
}
