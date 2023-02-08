package com.salonService.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Card {
	@Id
	private long id;
	private String cardName;
	private String cardNumber;
	private LocalDate expiryDate;
	private String bankName;
	private int cvv;
	@OneToOne
	private Payment payment;
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Card(long id, String cardName, String cardNumber, LocalDate expiryDate, String bankName, int cvv,
			Payment payment) {
		super();
		this.id = id;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.cvv = cvv;
		this.payment = payment;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
}
