package com.salonService.app.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ServiceCart {
	
	@Id
	//@GeneratedValue
	private Long id;
	private Double amount;

	@ManyToMany
	private List<SalonService> serviceList=new ArrayList<>();
	public ServiceCart() {
		super();
	
	}
	public ServiceCart(Long id, Double amount, List<SalonService> serviceList) {
		super();
		this.id = id;
		this.amount = amount;
		this.serviceList = serviceList;
	}
	public ServiceCart(Long id, Double amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public List<SalonService> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<SalonService> serviceList) {
		this.serviceList = serviceList;
	}

}
