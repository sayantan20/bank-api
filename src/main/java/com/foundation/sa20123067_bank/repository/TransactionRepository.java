package com.foundation.sa20123067_bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundation.sa20123067_bank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    public Optional<List<Transaction>> findByAccountNumber(Long accountNumber);

	
    
}
