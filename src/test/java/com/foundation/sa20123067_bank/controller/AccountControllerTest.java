package com.foundation.sa20123067_bank.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
import com.foundation.sa20123067_bank.doamin.AccountInformation;
import com.foundation.sa20123067_bank.doamin.AddressDetails;
import com.foundation.sa20123067_bank.doamin.BankInformation;
import com.foundation.sa20123067_bank.doamin.TransferDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Sa20123067BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testgetAccount() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/123", HttpMethod.GET, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(notNullValue()));
	}

	@Test
	public void testgetAccount1() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/564561631368", HttpMethod.GET,
				entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), is(notNullValue()));
	}
	

	@Test
	public void testgetAccountAddd() {
		AddressDetails address = new AddressDetails();
		address.setAddress1("Test");
		address.setAddress2("test1");
		address.setCity("City");
		address.setCountry("Country");
		address.setState("state");
		address.setZip("713205");
		BankInformation bankinfo = new BankInformation();
		bankinfo.setBranchAddress(address);
		bankinfo.setBranchCode(532);
		bankinfo.setBranchName("Yes Bank");
		AccountInformation acc = new AccountInformation();
		acc.setAccountBalance(1000.0);
		acc.setAccountNumber(12345L);
		acc.setAccountStatus("Active");
		acc.setAccountType("Savings");
		acc.setBankInformation(bankinfo);

		//HttpHeaders headers = new HttpHeaders();
		HttpEntity<AccountInformation> entity = new HttpEntity<AccountInformation>(acc);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/add/1001", HttpMethod.POST,
				entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertThat(response.getBody(), is(notNullValue()));
	}
	@Test
	public void testgetAccountAdd() {
		AddressDetails address = new AddressDetails();
		address.setAddress1("Test");
		address.setAddress2("test1");
		address.setCity("City");
		address.setCountry("Country");
		address.setState("state");
		address.setZip("713205");
		BankInformation bankinfo = new BankInformation();
		bankinfo.setBranchAddress(address);
		bankinfo.setBranchCode(532);
		bankinfo.setBranchName("Yes Bank");
		AccountInformation acc = new AccountInformation();
		acc.setAccountBalance(1000.0);
		acc.setAccountNumber(12345L);
		acc.setAccountStatus("Active");
		acc.setAccountType("Savings");
		acc.setBankInformation(bankinfo);

		//HttpHeaders headers = new HttpHeaders();
		HttpEntity<AccountInformation> entity = new HttpEntity<AccountInformation>(acc);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/add/1001", HttpMethod.POST,
				entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.ALREADY_REPORTED));
		assertThat(response.getBody(), is(notNullValue()));
	}
	
	@Test
	public void testgetAccountAddNoCustomer() {
		AddressDetails address = new AddressDetails();
		address.setAddress1("Test");
		address.setAddress2("test1");
		address.setCity("City");
		address.setCountry("Country");
		address.setState("state");
		address.setZip("713205");
		BankInformation bankinfo = new BankInformation();
		bankinfo.setBranchAddress(address);
		bankinfo.setBranchCode(532);
		bankinfo.setBranchName("Yes Bank");
		AccountInformation acc = new AccountInformation();
		acc.setAccountBalance(1000.0);
		acc.setAccountNumber(12345L);
		acc.setAccountStatus("Active");
		acc.setAccountType("Savings");
		acc.setBankInformation(bankinfo);

		//HttpHeaders headers = new HttpHeaders();
		HttpEntity<AccountInformation> entity = new HttpEntity<AccountInformation>(acc);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/add/1010", HttpMethod.POST,
				entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(notNullValue()));
	}
	@Test
	public void testgetAccountAddMinBal() {
		AddressDetails address = new AddressDetails();
		address.setAddress1("Test");
		address.setAddress2("test1");
		address.setCity("City");
		address.setCountry("Country");
		address.setState("state");
		address.setZip("713205");
		BankInformation bankinfo = new BankInformation();
		bankinfo.setBranchAddress(address);
		bankinfo.setBranchCode(532);
		bankinfo.setBranchName("Yes Bank");
		AccountInformation acc = new AccountInformation();
		acc.setAccountBalance(10.0);
		acc.setAccountNumber(12345L);
		acc.setAccountStatus("Active");
		acc.setAccountType("Savings");
		acc.setBankInformation(bankinfo);

		//HttpHeaders headers = new HttpHeaders();
		HttpEntity<AccountInformation> entity = new HttpEntity<AccountInformation>(acc);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/add/1001", HttpMethod.POST,
				entity, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.METHOD_NOT_ALLOWED));
		assertThat(response.getBody(), is(notNullValue()));
	}
	
	
	@Test
	public void testTrans() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transactions/564561631367", HttpMethod.GET, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), is(notNullValue()));
	}
	
	@Test
	public void testDeposite() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/deposite/564561631367?amount=100", HttpMethod.POST, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), is(notNullValue()));
	}
	
	@Test
	public void testDepositeNoAcc() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/deposite/464561631367?amount=100", HttpMethod.POST, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(notNullValue()));
	}
	@Test
	public void testDepositeNegBal() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/deposite/564561631367?amount=-100", HttpMethod.POST, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
		assertThat(response.getBody(), is(notNullValue()));
	
	}
	@Test
	public void testWithdrawalNoAccBal() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/withdrawal/564561631367?amount=150000", HttpMethod.POST, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));
		assertThat(response.getBody(), is(notNullValue()));

	}
	@Test
	public void testWithdrawalNoAcc() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/withdrawal/56456163367?amount=150", HttpMethod.POST, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(notNullValue()));

	}
	@Test
	public void testWithdrawalNegBal() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/withdrawal/564561631367?amount=-1000", HttpMethod.POST, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.FORBIDDEN));
		assertThat(response.getBody(), is(notNullValue()));

	}
	
	@Test
	public void fundTransfer() throws Exception{
		
	TransferDetails transfer= new TransferDetails();
	transfer.setFromAccountNumber(564561631367L);
	transfer.setToAccountNumber(564561631368L);
	transfer.setTransferAmount(100.0);
	HttpEntity<TransferDetails> entity = new HttpEntity<TransferDetails>(transfer);
	ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/1004", HttpMethod.PUT,entity,String.class);
	assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	assertThat(response.getBody(), is(notNullValue()));
	}
	@Test
	public void fundTransferInsufficent() throws Exception{
		
	TransferDetails transfer= new TransferDetails();
	transfer.setFromAccountNumber(564561631367L);
	transfer.setToAccountNumber(564561631368L);
	transfer.setTransferAmount(51000.0);
	HttpEntity<TransferDetails> entity = new HttpEntity<TransferDetails>(transfer);
	ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/1004", HttpMethod.PUT,entity,String.class);
	assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
	assertThat(response.getBody(), is(notNullValue()));
	}
	@Test
	public void fundTransferAccountFromNotFound() throws Exception{
		
	TransferDetails transfer= new TransferDetails();
	transfer.setFromAccountNumber(564562231367L);
	transfer.setToAccountNumber(564561631368L);
	transfer.setTransferAmount(51000.0);
	HttpEntity<TransferDetails> entity = new HttpEntity<TransferDetails>(transfer);
	ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/1004", HttpMethod.PUT,entity,String.class);
	assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
	assertThat(response.getBody(), is(notNullValue()));
	}
	
	@Test
	public void fundTransferAccounttoNotFound() throws Exception{
		
	TransferDetails transfer= new TransferDetails();
	transfer.setFromAccountNumber(564561631368L);
	transfer.setToAccountNumber(5645616442328L);
	transfer.setTransferAmount(51000.0);
	HttpEntity<TransferDetails> entity = new HttpEntity<TransferDetails>(transfer);
	ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transfer/1002", HttpMethod.PUT,entity,String.class);
	assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
	assertThat(response.getBody(), is(notNullValue()));
	}
	
	//564561631367
	

	@Test
	public void testPassBook() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transactions/564561631367", HttpMethod.GET, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), is(notNullValue()));

	}
	

	@Test
	public void testgetAccountBal() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transactions/balance/564561631367", HttpMethod.GET, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), is(notNullValue()));
	}

	@Test
	public void testgetAccountBalNo() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/transactions/balance/564222367", HttpMethod.GET, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(notNullValue()));
	}
	
	@Test
	public void AllAccount() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/all", HttpMethod.GET, entity,
				String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), is(notNullValue()));
	}
	
}
