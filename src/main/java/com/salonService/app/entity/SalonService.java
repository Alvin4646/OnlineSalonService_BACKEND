package com.salonService.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SalonService {
	@Id
	private Long serviceId;
	private String seviceName;
	private String servicePrice;
	private String serviceDuration;
	public SalonService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalonService(Long serviceId, String seviceName, String servicePrice, String serviceDuration) {
		super();
		this.serviceId = serviceId;
		this.seviceName = seviceName;
		this.servicePrice = servicePrice;
		this.serviceDuration = serviceDuration;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getSeviceName() {
		return seviceName;
	}
	public void setSeviceName(String seviceName) {
		this.seviceName = seviceName;
	}
	public String getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String getServiceDuration() {
		return serviceDuration;
	}
	public void setServiceDuration(String serviceDuration) {
		this.serviceDuration = serviceDuration;
	}
	
}
