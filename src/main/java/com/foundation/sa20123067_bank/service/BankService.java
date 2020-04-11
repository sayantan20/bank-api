package com.foundation.sa20123067_bank.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.foundation.sa20123067_bank.doamin.AccountInformation;
import com.foundation.sa20123067_bank.doamin.CustomerDetails;
import com.foundation.sa20123067_bank.doamin.TransactionDetails;
import com.foundation.sa20123067_bank.doamin.TransferDetails;

public interface BankService {

	
	public ResponseEntity<Object> findByAccountNumber(Long accountNumber);
	
	public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber);

	public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber);

	public List<CustomerDetails> findAllCustomer();

	public ResponseEntity<Object> addCustomer(CustomerDetails customer);

	public ResponseEntity<Object> findByCustomerNumber(Long customerNumber);

	public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber);

	public ResponseEntity<Object> deleteCustomer(Long customerNumber);

	public ResponseEntity<Object> depositeAmount(Long accountNumber, Double amount);

	public ResponseEntity<Object> withdrawalAmount(Long accountNumber, Double amount);

	public ResponseEntity<Object> addAccount(AccountInformation accountInformation, Long customerNumber);

	public ResponseEntity<String> getBalanceOf(Long accountNumber);
	
	public List<AccountInformation> findAllAccount();
}


