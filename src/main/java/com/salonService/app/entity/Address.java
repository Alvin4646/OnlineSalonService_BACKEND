package com.salonService.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Address {
	@Id
	private Long addressId;
	private String doorNo;
	private String street;
	private String area;
	private String city;
	private String state;
	
	int pincode;
	public Address(Long addressId, String doorNo, String street, String area, String city, String state, int pincode) {
		super();
		this.addressId = addressId;
		this.doorNo = doorNo;
		this.street = street;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [doorNo=" + doorNo + ", area=" + area + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + "]";
	}
	

}
