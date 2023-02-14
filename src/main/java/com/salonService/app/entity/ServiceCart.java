package com.salonService.app.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ServiceCart {
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	//@GeneratedValue
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
