package com.foundation.sa20123067_bank.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foundation.sa20123067_bank.dao.Dao;
import com.foundation.sa20123067_bank.doamin.AccountInformation;
import com.foundation.sa20123067_bank.doamin.CustomerDetails;
import com.foundation.sa20123067_bank.doamin.TransactionDetails;
import com.foundation.sa20123067_bank.doamin.TransferDetails;
import com.foundation.sa20123067_bank.entity.Account;
import com.foundation.sa20123067_bank.entity.Address;
import com.foundation.sa20123067_bank.entity.Contact;
import com.foundation.sa20123067_bank.entity.Customer;
import com.foundation.sa20123067_bank.entity.Transaction;
import com.foundation.sa20123067_bank.service.BankService;
import com.foundation.sa20123067_bank.util.BankingServiceHelper;

@Service
public class ServiceImpl implements BankService {

	@Autowired
	private Dao dao;

	@Autowired
	private BankingServiceHelper bankingServiceHelper;

	@Override
	public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {

		Optional<Account> accountEntityOpt = dao.findByAccountNumber(accountNumber);
		if (accountEntityOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(bankingServiceHelper.convertToAccountDomain(accountEntityOpt.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number " + accountNumber + " not found.");
		}
	}

	@Override
	public ResponseEntity<Object> addAccount(AccountInformation accountInformation, Long customerNumber) {
		Optional<Customer> customerEntityOpt = dao.findByCustomerNumber(customerNumber);
		Optional<Account> accountEntityOpt = dao.findByAccountNumber(accountInformation.getAccountNumber());
		if (customerEntityOpt.isPresent()) {
			if (accountEntityOpt.isPresent()) {
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
						.body("The Account already exist with the Customer");
			} else {
				if (accountInformation.getAccountBalance() >= 100) {
					dao.saveAccountInfo(accountInformation);
					dao.saveCustAccXRef(accountInformation, customerNumber);
					return ResponseEntity.status(HttpStatus.CREATED).body("New Account created successfully.");
				} else {
					return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
							.body("Please enter the minimum balnace i.e. Rs 100");
				}
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Customer Number doesn't exist");
	}

	@Override
	public ResponseEntity<Object> depositeAmount(Long accountNumber, Double amount) {
		List<Account> accountEntities = new ArrayList<>();
		Account toAccountEntity = null;
		Optional<Account> toAccountEntityOpt = dao.findByAccountNumber(accountNumber);
		if (toAccountEntityOpt.isPresent()) {
			if (amount >= 0) {
				toAccountEntity = toAccountEntityOpt.get();

				toAccountEntity.setAccountBalance(toAccountEntity.getAccountBalance() + amount);
				toAccountEntity.setUpdateDateTime(new Date());
				accountEntities.add(toAccountEntity);
				dao.saveALLAccountEntities(accountEntities);

				// Create transaction for TO Account
				Transaction toTransaction = bankingServiceHelper.depositeWithdrawalTransaction(amount,
						toAccountEntity.getAccountNumber(), "CREDIT");
				dao.fundTransferSave(toTransaction);
				return ResponseEntity.status(HttpStatus.OK).body("Success: Amount depsosited to " + accountNumber);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please eneter a valid Number");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number " + accountNumber + " not found.");
		}
	}

	@Override
	public ResponseEntity<Object> withdrawalAmount(Long accountNumber, Double amount) {
		List<Account> accountEntities = new ArrayList<>();
		Account fromAccountEntity = null;
		Optional<Account> fromAccountEntityOpt = dao.findByAccountNumber(accountNumber);
		if (fromAccountEntityOpt.isPresent()) {
			fromAccountEntity = fromAccountEntityOpt.get();
			if (fromAccountEntity.getAccountBalance() >= amount) {
				if (amount >= 0) {
					fromAccountEntity.setAccountBalance(fromAccountEntity.getAccountBalance() - amount);
					fromAccountEntity.setUpdateDateTime(new Date());
					accountEntities.add(fromAccountEntity);
					Transaction fromTransaction = bankingServiceHelper.depositeWithdrawalTransaction(amount,
							fromAccountEntity.getAccountNumber(), "DEBIT");
					dao.fundTransferSave(fromTransaction);

					return ResponseEntity.status(HttpStatus.OK)
							.body("Success: Amount withdrawal done from " + accountNumber);
				} else {
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please eneter a valid Number");
				}
			} else
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
						.body("Failure you can't withdraw more than " + fromAccountEntity.getAccountBalance());

		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number " + accountNumber + " not found.");
	}

	@Override
	public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber) {
		List<Account> accountEntities = new ArrayList<>();
		Account fromAccountEntity = null;
		Account toAccountEntity = null;

		Optional<Customer> customerEntityOpt = dao.findByCustomerNumber(customerNumber);

		// If customer is present
		if (customerEntityOpt.isPresent()) {

			// get FROM ACCOUNT info
			Optional<Account> fromAccountEntityOpt = dao.findByAccountNumber(transferDetails.getFromAccountNumber());
			if (fromAccountEntityOpt.isPresent()) {
				fromAccountEntity = fromAccountEntityOpt.get();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("From Account Number " + transferDetails.getFromAccountNumber() + " not found.");
			}

			// get TO ACCOUNT info
			Optional<Account> toAccountEntityOpt = dao.findByAccountNumber(transferDetails.getToAccountNumber());
			if (toAccountEntityOpt.isPresent()) {
				toAccountEntity = toAccountEntityOpt.get();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("To Account Number " + transferDetails.getToAccountNumber() + " not found.");
			}

			// if not sufficient funds, return 400 Bad Request
			if (fromAccountEntity.getAccountBalance() < transferDetails.getTransferAmount()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Funds.");
			} else {
				synchronized (this) {
					// update FROM ACCOUNT
					fromAccountEntity.setAccountBalance(
							fromAccountEntity.getAccountBalance() - transferDetails.getTransferAmount());
					fromAccountEntity.setUpdateDateTime(new Date());
					accountEntities.add(fromAccountEntity);

					// update TO ACCOUNT
					toAccountEntity.setAccountBalance(
							toAccountEntity.getAccountBalance() + transferDetails.getTransferAmount());
					toAccountEntity.setUpdateDateTime(new Date());
					accountEntities.add(toAccountEntity);
					dao.saveALLAccountEntities(accountEntities);

					// Create transaction for FROM Account
					Transaction fromTransaction = bankingServiceHelper.createTransaction(transferDetails,
							fromAccountEntity.getAccountNumber(), "DEBIT");
					dao.fundTransferSave(fromTransaction);
					// transactionRepository.save(fromTransaction);

					// Create transaction for TO Account
					Transaction toTransaction = bankingServiceHelper.createTransaction(transferDetails,
							toAccountEntity.getAccountNumber(), "CREDIT");
					dao.fundTransferSave(toTransaction);
					// transactionRepository.save(toTransaction);
				}

				return ResponseEntity.status(HttpStatus.OK)
						.body("Success: Amount transferred for Customer Number " + customerNumber);
			}

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Customer Number " + customerNumber + " not found.");
		}
	}

	@Override
	public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber) {
		List<TransactionDetails> transactionDetails = new ArrayList<>();
		Optional<Account> accountEntityOpt = dao.findByAccountNumber(accountNumber);
		if (accountEntityOpt.isPresent()) {
			Optional<List<Transaction>> transactionEntitiesOpt = dao.findByAccountNumberTransaction(accountNumber);
			if (transactionEntitiesOpt.isPresent()) {
				transactionEntitiesOpt.get().forEach(transaction -> {
					transactionDetails.add(bankingServiceHelper.convertToTransactionDomain(transaction));
				});
			}
		}
		return transactionDetails;
	}

	@Override
	public List<CustomerDetails> findAllCustomer() {

		List<CustomerDetails> allCustomerDetails = new ArrayList<>();
		Iterable<Customer> customerList = dao.findAllCustomer();
		customerList.forEach(customer -> {
			allCustomerDetails.add(bankingServiceHelper.convertToCustomerDomain(customer));
		});
		return allCustomerDetails;

	}

	@Override
	public List<AccountInformation> findAllAccount() {

		List<AccountInformation> allAccountsDetails = new ArrayList<>();
		Iterable<Account> accList = dao.findAllAccount();
		accList.forEach(account -> {
			allAccountsDetails.add(bankingServiceHelper.convertToAccountDomain(account));
		});
		return allAccountsDetails;

	}

	@Override
	public ResponseEntity<Object> addCustomer(CustomerDetails customerDetails) {
		Optional<Customer> customerEntityOpt = dao.findByCustomerNumber(customerDetails.getCustomerNumber());
		if (customerEntityOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The Customer Number is already added");
		} else {
			Customer customer = bankingServiceHelper.convertToCustomerEntity(customerDetails);
			customer.setCreateDateTime(new Date());
			dao.addCustomer(customer);
			Optional<Customer> addedcustomerByCustomerNo = dao
					.findByCustomerNumber(customerDetails.getCustomerNumber());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(bankingServiceHelper.convertToCustomerDomain(addedcustomerByCustomerNo.get()));
		}
	}

	@Override
	public ResponseEntity<Object> findByCustomerNumber(Long customerNumber) {
		Optional<Customer> customerEntityOpt = dao.findByCustomerNumber(customerNumber);
		if (customerEntityOpt.isPresent()) {
			CustomerDetails customer = bankingServiceHelper.convertToCustomerDomain(customerEntityOpt.get());
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No Customer is found against Customer Number: " + customerNumber);
		}
	}

	@Override
	public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber) {
		Optional<Customer> managedCustomerEntityOpt = dao.findByCustomerNumber(customerNumber);
		Customer unmanagedCustomerEntity = bankingServiceHelper.convertToCustomerEntity(customerDetails);
		if (managedCustomerEntityOpt.isPresent()) {
			Customer managedCustomerEntity = managedCustomerEntityOpt.get();
			if (Optional.ofNullable(unmanagedCustomerEntity.getContactDetails()).isPresent()) {
				Contact managedContact = managedCustomerEntity.getContactDetails();
				if (managedContact != null) {
					managedContact.setEmailId(unmanagedCustomerEntity.getContactDetails().getEmailId());
					managedContact.setHomePhone(unmanagedCustomerEntity.getContactDetails().getHomePhone());
					managedContact.setWorkPhone(unmanagedCustomerEntity.getContactDetails().getWorkPhone());
				} else
					managedCustomerEntity.setContactDetails(unmanagedCustomerEntity.getContactDetails());
			}
			if (Optional.ofNullable(unmanagedCustomerEntity.getCustomerAddress()).isPresent()) {
				Address managedAddress = managedCustomerEntity.getCustomerAddress();
				if (managedAddress != null) {
					managedAddress.setAddress1(unmanagedCustomerEntity.getCustomerAddress().getAddress1());
					managedAddress.setAddress2(unmanagedCustomerEntity.getCustomerAddress().getAddress2());
					managedAddress.setCity(unmanagedCustomerEntity.getCustomerAddress().getCity());
					managedAddress.setState(unmanagedCustomerEntity.getCustomerAddress().getState());
					managedAddress.setZip(unmanagedCustomerEntity.getCustomerAddress().getZip());
					managedAddress.setCountry(unmanagedCustomerEntity.getCustomerAddress().getCountry());
				} else
					managedCustomerEntity.setCustomerAddress(unmanagedCustomerEntity.getCustomerAddress());
			}
			managedCustomerEntity.setUpdateDateTime(new Date());
			managedCustomerEntity.setStatus(unmanagedCustomerEntity.getStatus());
			managedCustomerEntity.setFirstName(unmanagedCustomerEntity.getFirstName());
			managedCustomerEntity.setMiddleName(unmanagedCustomerEntity.getMiddleName());
			managedCustomerEntity.setLastName(unmanagedCustomerEntity.getLastName());
			managedCustomerEntity.setUpdateDateTime(new Date());

			Customer customerUpdated = dao.addCustomer(managedCustomerEntity);

			return ResponseEntity.status(HttpStatus.OK).body(customerUpdated);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Customer Number " + customerNumber + " not found.");
		}
	}

	@Override
	public ResponseEntity<Object> deleteCustomer(Long customerNumber) {
		Optional<Customer> managedCustomerEntityOpt = dao.findByCustomerNumber(customerNumber);
		if (managedCustomerEntityOpt.isPresent()) {
			Customer managedCustomerEntity = managedCustomerEntityOpt.get();
			dao.deleteCustomer(managedCustomerEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Success: Customer deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer does not exist.");
		}
	}

	@Override
	public ResponseEntity<String> getBalanceOf(Long accountNumber) {
		Optional<Account> accountEntityOpt = dao.findByAccountNumber(accountNumber);
		if (accountEntityOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(accountEntityOpt.get().getAccountBalance().toString());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Account Number " + accountNumber + " doesn't exist.");
		}
	}

}
