package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.domain.Customer;
import com.springboot.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public ResponseEntity<?> getCustomers(){
		List<Customer> customers = customerService.getCustomers();
		if(CollectionUtils.isEmpty(customers)) {
			return new ResponseEntity<String>("customer details are not present ", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@RequestMapping(value="addcustomer",method=RequestMethod.POST)
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
		String response = customerService.addCustomer(customer);
		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}
	

}
