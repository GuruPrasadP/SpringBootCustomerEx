package com.springboot.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Customer;

@Repository
public class CustomerRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Customer> retrieveCustomers() {
		/*List<Customer> customers = new ArrayList<Customer>(){
			{
				add(createCustomer("id1","name1"));
				add(createCustomer("id2","name2"));
				add(createCustomer("id3","name3"));
			}
		};*/
		List<Customer> customers = mongoTemplate.findAll(Customer.class);
		
		return customers;
	}

	private Customer createCustomer(String customerId, String customerName) {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName(customerName);
		return customer;
	}

	public String saveCustomer(Customer customer) {
		mongoTemplate.save(customer, "customer");
		return "customer added successfully";
	}
	
	

}
