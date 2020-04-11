package com.foundation.sa20123067_bank.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.foundation.sa20123067_bank.Sa20123067BankApplication;
import com.foundation.sa20123067_bank.doamin.AddressDetails;
import com.foundation.sa20123067_bank.doamin.ContactDetails;
import com.foundation.sa20123067_bank.doamin.CustomerDetails;
import com.foundation.sa20123067_bank.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Sa20123067BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testGetAllCustomer() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/customers/all", HttpMethod.GET, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), is(notNullValue()));

	}

	@Test
	public void testAddCustomer() throws Exception{

		AddressDetails address= new AddressDetails();
		address.setAddress1("Test");
		address.setAddress2("test1");
		address.setCity("City");
		address.setCountry("Country");
		address.setState("state");
		address.setZip("713205");
		ContactDetails conD= new ContactDetails("test@tes.com","5641239640","120120120");
		CustomerDetails customer = new CustomerDetails("Sayantan","Mitra","Jr",1008L,"AGYMP4001A","Active",address,conD);
		
		
		HttpEntity<CustomerDetails> request= new HttpEntity<>(customer);
		ResponseEntity<Customer> response = restTemplate.postForEntity(getRootUrl() + "/customers/add", request, Customer.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertNotNull(response);

	}
	
	@Test
	public void testById() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Customer> entity = new HttpEntity<Customer>(null, headers);
		ResponseEntity<Customer> response = restTemplate.exchange(getRootUrl() + "/customers/find/1004",HttpMethod.GET, entity, Customer.class);	
		assertThat(response.getBody(), is(notNullValue()));	
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	

}
