package com.foundation.sa20123067_bank.dao;

import java.util.List;
import java.util.Optional;

import com.foundation.sa20123067_bank.doamin.AccountInformation;
import com.foundation.sa20123067_bank.entity.Account;
import com.foundation.sa20123067_bank.entity.Customer;
import com.foundation.sa20123067_bank.entity.CustomerAccountXRef;
import com.foundation.sa20123067_bank.entity.Transaction;

public interface Dao {
	

	public Optional<Account> findByAccountNumber(Long accountNumber);

	public Optional<Customer> findByCustomerNumber(Long customerNumber);

	public Account saveAccountInfo(AccountInformation accountInformation);

	public CustomerAccountXRef saveCustAccXRef(AccountInformation accountInformation, Long customerNumber);

	public List<Account> saveALLAccountEntities(List<Account> accountEntities);

	public Transaction fundTransferSave(Transaction Transaction);

	public Optional<List<Transaction>> findByAccountNumberTranscation(Long accountNumber);

	public Iterable<Customer> findAllCustomer();
	
	public Iterable<Account> findAllAccount();

	public Customer addCustomer(Customer customer);

	public void deleteCustomer(Customer managedCustomerEntity);

	public Optional<List<Transaction>> findByAccountNumberTransaction(Long accountNumber);

	

}
