package com.springboot.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customer")
public class Customer {
	
	@Id
	private String customerId;
	private String customerName;
	private List<Address> addresses;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	

}
