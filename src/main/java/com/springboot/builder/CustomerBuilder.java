package com.springboot.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.Builder;
import org.assertj.core.util.Lists;

import com.springboot.domain.Address;
import com.springboot.domain.Customer;

public class CustomerBuilder implements Builder<Customer>{

	private String customerId = "default customerId";
	private String customerName = "default customeName";
	private List<AddressBuilder> addresses = Lists.newArrayList();
	
	@Override
	public Customer build() {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName(customerName);
		List<Address> addresses1 = this.addresses.stream()
												.map(addr -> addr.build())
												.collect(Collectors.toList());
		/*List<Address> addresses = Lists.newArrayList();
		this.addresses.forEach(addr -> addresses.add(addr.build()));*/
		
		customer.setAddresses(addresses1);
		return customer;
	}
	
	public AddressBuilder addAddress() {
		AddressBuilder address = new AddressBuilder(this);
		addresses.add(address);
		return address;
	}
	
	public CustomerBuilder withCustomerId(String customerId){
		this.customerId = customerId;
		return this;
	}
	
	public CustomerBuilder withCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	
}
