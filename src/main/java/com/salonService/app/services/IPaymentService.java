package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Payment;

public interface IPaymentService {
	public Payment getPaymentById(long paymentId) ;

	public Payment addPayment(Payment payment);

	public Payment deletePayment(long paymentId) ;

	public Payment updatePayment(long paymentId, Payment payment) ;

	public List<Payment> getAllPaymentDetails() ;
}
