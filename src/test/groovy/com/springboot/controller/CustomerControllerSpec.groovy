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

import com.springboot.domain.Customer
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
//@Ignore
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
    
    }