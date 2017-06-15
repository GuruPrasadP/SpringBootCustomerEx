package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.domain.Customer;
import com.springboot.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getCustomers() {
		return customerRepository.retrieveCustomers();
	}

	public String addCustomer(Customer customer) {
		
		return customerRepository.saveCustomer(customer);
	}

}
