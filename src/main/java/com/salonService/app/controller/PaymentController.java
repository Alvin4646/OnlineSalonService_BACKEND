package com.salonService.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.salonService.app.entity.Payment;
import com.salonService.app.exception.AppointmentException;
import com.salonService.app.exception.PaymentException;
import com.salonService.app.services.IAppointmentService;
import com.salonService.app.services.IPaymentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
	@Autowired
	private IPaymentService iPaymentService;
	@Autowired
	private IAppointmentService iAppointmentService;
 

	@PostMapping("/payment/{id}")
	public Payment addAppointmentToCustomer(@RequestBody Payment appointment,@PathVariable long id) throws PaymentException {
		return this.iPaymentService.addPaymentToAppointment(appointment, id); 
	}


	@DeleteMapping("/deletePayment/{appointmentId}")
	public Payment removePaymentFromAppointment(@PathVariable Long appointmentId) throws AppointmentException {
		return iAppointmentService.removePaymenttByid(appointmentId);
	}

	@GetMapping("/payments")
	public List<Payment> findAllPayment() throws PaymentException {
		return iPaymentService.getAllPaymentDetails();
	}

	@PutMapping("/updatepayment/{id}")
	public Payment updateEmployee(@RequestBody Payment payment, @PathVariable long id) throws PaymentException {
		return iPaymentService.updatePayment(id, payment);
	}

	@GetMapping("/payment/{id}")
	public Payment findEmployee(@PathVariable long id)throws PaymentException {
		return iPaymentService.getPaymentById(id);
	}
	
	
}
