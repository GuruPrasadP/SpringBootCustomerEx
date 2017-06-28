package com.springboot.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import java.util.List

import com.springboot.builder.AddressBuilder
import com.springboot.builder.CustomerBuilder
import com.springboot.domain.Customer
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource


@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource("/testapplication.properties")
@Ignore
class CustomerControllerSpec extends Specification {
    
    @Autowired
    TestRestTemplate restTemplate;
    
     def 'should return customers'() {
        when:
        ResponseEntity<List<Customer>> response = restTemplate.getForEntity('/customers', List.class);

        then:
        response.statusCode == HttpStatus.OK;
		List<Customer> customers = response.body;
		customers.size() > 0;
		for(Customer customer in customers) {
			customer.getCustomerId() != null;
		}
    }
	
	def 'add customer'() {
		given :
		Customer customer = new CustomerBuilder()
								.addAddress().done().build();
		when:
		ResponseEntity<String> response = restTemplate.postForEntity("/addcustomer", customer, String.class);
		
		then:
		response.statusCode == HttpStatus.OK;
		response.body == "customer added successfully"
    }
	
}