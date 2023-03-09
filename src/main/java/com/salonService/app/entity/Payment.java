package com.salonService.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payment {
	@Id
	@GeneratedValue
	private Long paymentId;
	private ModeOfPayment type;
	private PaymentStatus status;	
	private double amount;
	
	public Payment() {
		super(); 
	}
	public Payment(Long paymentId, ModeOfPayment type, PaymentStatus status,double amount) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		this.amount=amount;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public ModeOfPayment getType() {
		return type;
	}
	public void setType(ModeOfPayment type) {
		this.type = type;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", type=" + type + ", status=" + status + "]";
	}
	
	public enum ModeOfPayment{
		CASH,CARD
	}
	public enum PaymentStatus{
		PENDING,PAID
	}

}
