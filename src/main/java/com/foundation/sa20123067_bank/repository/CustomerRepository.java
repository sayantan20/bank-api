package com.foundation.sa20123067_bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundation.sa20123067_bank.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    public Optional<Customer> findByCustomerNumber(Long customerNumber);

	
    
}
