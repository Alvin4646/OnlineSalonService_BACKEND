package com.salonService.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Customer;
import com.salonService.app.repository.ICustomerRepository;
import com.salonService.app.services.ICustomerService;
@RestController
public class CustomerController {
@Autowired
	private ICustomerService iCustomerService;
@PostMapping("/customer")
public Customer addCustomer(@RequestBody Customer customer) {
	return this.iCustomerService.addCustomer(customer);
}

@DeleteMapping("/deleteCustomer/(id)")
public void deleteCustomer(@PathVariable int id) {
	 iCustomerService.deleteCustomer(id);
}

@GetMapping("/customerById/(id)")
public Customer findCustomerById(@PathVariable int id) {
	return iCustomerService.getCustomer(id);
}
@GetMapping("/customer")
public List<Customer> getAllCustomers(){
	return iCustomerService.getAllCustomers();
}
@PutMapping("/updateCustomers/(id)")
public Customer updateCustomer(@RequestBody Customer customer,@PathVariable int id) {
	return iCustomerService.updateCustomer(id, customer);
}
}
