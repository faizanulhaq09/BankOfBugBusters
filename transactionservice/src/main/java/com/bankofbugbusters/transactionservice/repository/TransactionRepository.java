package com.bankofbugbusters.transactionservice.repository;

import com.bankofbugbusters.transactionservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {
}
