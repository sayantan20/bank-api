package com.foundation.sa20123067_bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foundation.sa20123067_bank.doamin.CustomerDetails;
import com.foundation.sa20123067_bank.service.BankService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customers")
@CrossOrigin(maxAge = 72000)
@Api(tags = { "Customer endpoints" })
public class CustomerController {

	@Autowired
	private BankService service;

	@GetMapping(path = "/all")
	@ApiOperation(value = "Find all customers", notes = "Gets details of all the customers")
	public List<CustomerDetails> getAllCustomers() {

		return service.findAllCustomer();
	}

	@PostMapping(path = "/add")
	@ApiOperation(value = "Add a Customer", notes = "Add customer and create an account")
	public ResponseEntity<Object> addCustomer(@RequestBody CustomerDetails customer) {
		return service.addCustomer(customer);
	}

	@GetMapping(path = "/find/{customerNumber}")
	@ApiOperation(value = "Get customer details", notes = "Get Customer details by customer number.")
	public ResponseEntity<Object> getCustomer(@PathVariable Long customerNumber) {
		return service.findByCustomerNumber(customerNumber);
	}

	@PutMapping(path = "/update/{customerNumber}")
	@ApiOperation(value = "Update customer", notes = "Update customer and any other account information associated with him.")
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerDetails customerDetails,
			@PathVariable Long customerNumber) {

		return service.updateCustomer(customerDetails, customerNumber);
	}

	@DeleteMapping(path = "/delete/{customerNumber}")
	@ApiOperation(value = "Delete customer and related accounts", notes = "Delete customer and all accounts associated with him.")	
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long customerNumber) {

		return service.deleteCustomer(customerNumber);
	}

}
