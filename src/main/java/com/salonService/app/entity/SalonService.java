package com.salonService.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class SalonService {
	
	@Id
	@GeneratedValue
	@Min(value = 1, message = "id value must be > 0")
	private Long serviceId;
	@NotNull(message = "service name must not be null")
	private String seviceName;
	@Size(min = 1, max = 3, message = "price must be >0 <1000")
	@Pattern(regexp = "^[0-9]*$", message = "service price has only digits")
	@NotNull(message = "service price must not be null")
	private String servicePrice;
	@Pattern(regexp = "^[0-9]*$", message = "service duration must be >0")
	@NotNull(message = "service duration must not be null")
	@Size(min = 1, max = 2, message = "duration should be valid")
	private String serviceDuration;

	public SalonService() {
		super();
	
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
