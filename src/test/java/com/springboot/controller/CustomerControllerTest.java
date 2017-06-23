package com.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.springboot.builder.CustomerBuilder;
import com.springboot.domain.Customer;
import com.springboot.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	@InjectMocks
	private CustomerController customerController;
	
	@Mock
	private CustomerService customerService;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void shouldReturnHttpStatusNotFoundWhenCustomerDetailsAreEmpty() throws Exception {
		mockMvc.perform(get("/customers"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldGetAllCustomersDetails() throws Exception {
		List<Customer> input = Lists.newArrayList(new CustomerBuilder().addAddress().done().build());
		when(customerService.getCustomers()).thenReturn(input);
		MockHttpServletResponse response = mockMvc.perform(get("/customers"))
				.andExpect(status().isOk())
				.andReturn().getResponse();
		assertNotNull(response);
		Gson gson = new Gson();
		assertEquals(gson.toJson(input), response.getContentAsString());
	}

}
