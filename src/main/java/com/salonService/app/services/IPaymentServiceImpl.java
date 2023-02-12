package com.salonService.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Payment;
import com.salonService.app.exception.PaymentException;
import com.salonService.app.repository.IAppointmentRepository;
import com.salonService.app.repository.IPaymentRepository;

@Service
public class IPaymentServiceImpl implements IPaymentService {
	@Autowired
	private IPaymentRepository iPaymentRepository;
	@Autowired
	private IAppointmentRepository iAppointmentRepository;

	@Override
	public Payment getPaymentById(long paymentId)throws PaymentException {
		Optional<Payment> pay = iPaymentRepository.findById(paymentId);
		if (pay.isPresent()) {
			return pay.get();
		} else {
			throw new PaymentException("Payment with idv"+paymentId+" was not found");
		}
	}

	@Override
	public Payment addPayment(Payment payment) {
		// TODO Auto-generated method stub
		return iPaymentRepository.save(payment);
	}

	@Override
	public Payment deletePayment(long paymentId)throws PaymentException {
		Optional<Payment> PaymentToBeDeleted = iPaymentRepository.findById(paymentId);
		iPaymentRepository.deleteById(paymentId);

		if (PaymentToBeDeleted.isPresent()) {
			return PaymentToBeDeleted.get();
		} else {
			return null;
		}

	}

	@Override
	public Payment updatePayment(long paymentId, Payment payment) {

		if (iPaymentRepository.existsById(paymentId)) {
			Payment paymentToBeUpdated = iPaymentRepository.findById(paymentId).get();
			paymentToBeUpdated.setStatus(payment.getStatus());
			paymentToBeUpdated.setType(payment.getType());
			iPaymentRepository.save(paymentToBeUpdated);
			return paymentToBeUpdated;
		}
		return null;

	}

	@Override
	public List<Payment> getAllPaymentDetails() {
		// TODO Auto-generated method stub
		return iPaymentRepository.findAll();
	}

	@Override
	public Payment addPaymentToAppointment(Payment payment,long id) {
		Optional<Appointment> existAppointment=iAppointmentRepository.findById(id);
		if(existAppointment.isEmpty()) {
			//Exception
		}
		Appointment foundAppointment=existAppointment.get();
		Payment newPayment=addPayment(payment);
		foundAppointment.setPayment(newPayment);
		iAppointmentRepository.save(foundAppointment);
		return payment;
	}

}
