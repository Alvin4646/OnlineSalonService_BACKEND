package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Payment;
import com.salonService.app.repository.IPaymentRepository;
@Service
public class IPaymentServiceImpl implements IPaymentService{
@Autowired
private IPaymentRepository iPaymentRepository;
	@Override
	public Payment getPaymentById(long paymentId) {
		Optional<Payment> pay= iPaymentRepository.findById(paymentId);
		if(pay.isPresent()) {
			return pay.get();
		}
		else {
		return null;
		}
	}

	@Override
	public Payment addPayment(Payment payment) {
		// TODO Auto-generated method stub
		return iPaymentRepository.save(payment);
	}

	@Override
	public Payment deletePayment(long paymentId) {
		Optional<Payment> PaymentToBeDeleted =  iPaymentRepository.findById(paymentId);
		iPaymentRepository.deleteById(paymentId);
		
		if(PaymentToBeDeleted.isPresent()) {
			return PaymentToBeDeleted.get();
		}
		else {
			return null;
		}
	
		
	}

	@Override
	public  Payment updatePayment(long paymentId, Payment payment) {

		if(iPaymentRepository.existsById(paymentId)) {
			Payment paymentToBeUpdated = iPaymentRepository.findById(paymentId).get();
			iPaymentRepository.save(payment);
			return paymentToBeUpdated;
		}
		return null;
		
		
	}

	@Override
	public List<Payment> getAllPaymentDetails() {
		// TODO Auto-generated method stub
		return iPaymentRepository.findAll();
	}

}
