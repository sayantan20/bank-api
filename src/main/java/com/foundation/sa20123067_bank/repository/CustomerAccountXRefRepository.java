package com.foundation.sa20123067_bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundation.sa20123067_bank.entity.CustomerAccountXRef;

@Repository
public interface CustomerAccountXRefRepository extends JpaRepository<CustomerAccountXRef, String> {
	
	public List<CustomerAccountXRef> findByCustomerNumber(Long customerNumber);
	
	public List<CustomerAccountXRef> findByAccountNumber(Long customerNumber);
	
}
