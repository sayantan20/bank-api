package com.foundation.sa20123067_bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundation.sa20123067_bank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	Optional<Account> findByAccountNumber(Long accountNumber);
}
