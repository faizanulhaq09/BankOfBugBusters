package com.bankofbugbusters.transactionservice.mapper;

import com.bankofbugbusters.transactionservice.dto.CreateTransactionRequest;
import com.bankofbugbusters.transactionservice.dto.TransactionResponse;
import com.bankofbugbusters.transactionservice.entity.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class TransactionMapper {
    public Transaction toEntity(CreateTransactionRequest request) {
        Transaction txn = new Transaction();
        txn.setFromAccountId(request.fromAccountId());
        txn.setToAccountId(request.toAccountId());
        txn.setAmount(request.amount());
        txn.setTransactionType(request.transactionType());
        txn.setTransactionDate(LocalDateTime.now()); // setting current timestamp
        return txn;
    }

    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getFromAccountId(),
                transaction.getToAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTransactionDate()
        );
    }
}
