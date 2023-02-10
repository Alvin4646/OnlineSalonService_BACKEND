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

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Payment;
import com.salonService.app.exception.PaymentException;
import com.salonService.app.services.IPaymentService;

@RestController
public class PaymentController {
	@Autowired
	private IPaymentService iPaymentService;

	@PostMapping("/payment")
	public Payment addPayment(@RequestBody Payment payment) {
		return this.iPaymentService.addPayment(payment);

	}
	@PostMapping("/payment/{id}")
	public Payment addAppointmentToCustomer(@RequestBody Payment appointment,@PathVariable long id) {
		return this.iPaymentService.addPaymentToAppointment(appointment, id);
	}

	@DeleteMapping("/deletePayment/(id)")
	public void deletePayment(@PathVariable int id) {
		 this.iPaymentService.deletePayment(id);
	}

	@GetMapping("/payments")
	public List<Payment> findAllPayment() {
		return iPaymentService.getAllPaymentDetails();
	}

	@PutMapping("/updatepayment")
	public Payment updateEmployee(@RequestBody Payment payment, @PathVariable long id) {
		return iPaymentService.updatePayment(id, payment);
	}

	@GetMapping("/payment/{id}")
	public Payment findEmployee(@PathVariable long id)throws PaymentException {
		return iPaymentService.getPaymentById(id);
	}
	
}
