package com.foundation.sa20123067_bank.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foundation.sa20123067_bank.doamin.AccountInformation;
import com.foundation.sa20123067_bank.doamin.CustomerDetails;
import com.foundation.sa20123067_bank.doamin.TransactionDetails;
import com.foundation.sa20123067_bank.doamin.TransferDetails;
import com.foundation.sa20123067_bank.service.BankService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(maxAge = 72000)
@Api(tags = { "Accounts and Transactions endpoints" })
public class AccountController {

	@Autowired
	private BankService service;

	@GetMapping(path = "/{accountNumber}")
	@ApiOperation(value = "Get account details", notes = "Find account details by account number")
	public ResponseEntity<Object> getByAccountNumber(@PathVariable Long accountNumber) throws EntityNotFoundException{

		return service.findByAccountNumber(accountNumber);
	}
	

	@PostMapping(path = "/add/{customerNumber}")
	public ResponseEntity<Object> addNewAccount(@PathVariable Long customerNumber,@RequestBody AccountInformation accountInformation) {

		return service.addAccount(accountInformation, customerNumber);
	}

	@PutMapping(path = "/transfer/{customerNumber}")
	@ApiOperation(value = "Transfer funds between accounts", notes = "Transfer funds between accounts.")
	public ResponseEntity<Object> transferDetails(@RequestBody TransferDetails transferDetails,
			@PathVariable Long customerNumber) {
		return service.transferDetails(transferDetails, customerNumber);
		
		
	}
	
	
	@PostMapping(path="/transfer/deposite/{accountNumber}")
	@ApiOperation(value = "Deposite amount in account", notes="Deposite bhy account number")
	public ResponseEntity<Object> depositeAmount(@PathVariable Long accountNumber, @RequestParam("amount") Double amount){
		return service.depositeAmount(accountNumber,amount);
	}
	
	@PostMapping(path="/transfer/withdrawal/{accountNumber}")
	@ApiOperation(value = "Withdrawal amount in account", notes="Withdrawal bhy account number")
	public ResponseEntity<Object> withdrawalAmount(@PathVariable Long accountNumber, @RequestParam("amount") Double amount){
		return service.withdrawalAmount(accountNumber,amount);
	}
	
	@GetMapping(path = "/transactions/{accountNumber}")
	@ApiOperation(value = "Get all transactions", notes = "Get all Transactions by account number")
	public List<TransactionDetails> getTransactionByAccountNumber(@PathVariable Long accountNumber) {

		return service.findTransactionsByAccountNumber(accountNumber);
	}
	
	@GetMapping(path="//transactions/balance/{accountNumber}")
	@ApiOperation(value = "Get the available balance", notes= "Get balance by account number")
	public ResponseEntity<String> getBalanceByAccountNumber(@PathVariable Long accountNumber){
		return service.getBalanceOf(accountNumber);
	}
	

	@GetMapping(path = "/all")
	@ApiOperation(value = "Find all Accounts", notes = "Gets details of all the accounts")
	public List<AccountInformation> getAllAccount() {

		return service.findAllAccount();
	}
}
