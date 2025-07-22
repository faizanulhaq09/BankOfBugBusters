package com.bankofbugbusters.transactionservice.service;

import com.bankofbugbusters.transactionservice.dto.CreateTransactionRequest;
import com.bankofbugbusters.transactionservice.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(CreateTransactionRequest request);
    TransactionResponse getTransactionById(Long id);
    List<TransactionResponse> getAllTransactions();
    TransactionResponse updateTransaction(Long id, CreateTransactionRequest request);
    void deleteTransaction(Long id);
}
