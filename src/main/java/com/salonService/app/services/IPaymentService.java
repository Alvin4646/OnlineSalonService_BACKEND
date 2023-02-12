package com.salonService.app.services;

import java.util.List;

import com.salonService.app.entity.Payment;
import com.salonService.app.exception.PaymentException;

public interface IPaymentService {
	public Payment getPaymentById(long paymentId)throws PaymentException ;

	public Payment addPayment(Payment payment);

	public Payment deletePayment(long paymentId)throws PaymentException ;

	public Payment updatePayment(long paymentId, Payment payment) ;

	public List<Payment> getAllPaymentDetails() ;
	
	public Payment addPaymentToAppointment(Payment payment,long id);
}
