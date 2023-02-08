package com.salonService.app.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ServiceCart {
	@Id
	private Long id;
	private Double amount;
//	@ManyToMany
//	private Map<Integer,SalonService> serviceMap=new HashMap<>();
	@ManyToMany
	private List<SalonService> serviceList=new ArrayList<>();
	public ServiceCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServiceCart(Long id, Double amount, List<SalonService> serviceList) {
		super();
		this.id = id;
		this.amount = amount;
		this.serviceList = serviceList;
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
