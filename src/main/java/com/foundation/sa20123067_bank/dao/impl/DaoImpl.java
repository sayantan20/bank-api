package com.foundation.sa20123067_bank.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foundation.sa20123067_bank.dao.Dao;
import com.foundation.sa20123067_bank.doamin.AccountInformation;
import com.foundation.sa20123067_bank.entity.Account;
import com.foundation.sa20123067_bank.entity.Customer;
import com.foundation.sa20123067_bank.entity.CustomerAccountXRef;
import com.foundation.sa20123067_bank.entity.Transaction;
import com.foundation.sa20123067_bank.repository.AccountRepository;
import com.foundation.sa20123067_bank.repository.CustomerAccountXRefRepository;
import com.foundation.sa20123067_bank.repository.CustomerRepository;
import com.foundation.sa20123067_bank.repository.TransactionRepository;
import com.foundation.sa20123067_bank.util.BankingServiceHelper;

@Repository
public class DaoImpl implements Dao {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerAccountXRefRepository customerAccountXRefRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private BankingServiceHelper bankingServiceHelper;

	@Override
	public Optional<Account> findByAccountNumber(Long accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public Optional<Customer> findByCustomerNumber(Long customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber);

	}

	@Override
	public Account saveAccountInfo(AccountInformation accountInformation) {
		return accountRepository.save(bankingServiceHelper.convertToAccountEntity(accountInformation));
	}

	@Override
	public CustomerAccountXRef saveCustAccXRef(AccountInformation accountInformation, Long customerNumber) {
		return customerAccountXRefRepository.save(CustomerAccountXRef.builder().accountNumber(accountInformation.getAccountNumber()).customerNumber(customerNumber).build());

	}

	@Override
	public List<Account> saveALLAccountEntities(List<Account> accountEntities) {
		return accountRepository.saveAll(accountEntities);
	}

	@Override
	public Transaction fundTransferSave(Transaction Transaction) {
		return transactionRepository.save(Transaction);
	}

	@Override
	public Optional<List<Transaction>> findByAccountNumberTranscation(Long accountNumber) {
		return transactionRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public Iterable<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Customer managedCustomerEntity) {
		customerRepository.delete(managedCustomerEntity);
		
	}

	@Override
	public Optional<List<Transaction>> findByAccountNumberTransaction(Long accountNumber) {
		return transactionRepository.findByAccountNumber(accountNumber);
		
	}

	@Override
	public Iterable<Account> findAllAccount() {
		return accountRepository.findAll();
	}

	
}
