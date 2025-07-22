package com.bankofbugbusters.transactionservice.service;

import com.bankofbugbusters.transactionservice.dto.CreateTransactionRequest;
import com.bankofbugbusters.transactionservice.dto.NotificationRequest;
import com.bankofbugbusters.transactionservice.dto.TransactionResponse;
import com.bankofbugbusters.transactionservice.entity.Transaction;
import com.bankofbugbusters.transactionservice.exception.ResourceNotFoundException;
import com.bankofbugbusters.transactionservice.kafka.NotificationProducer;
import com.bankofbugbusters.transactionservice.mapper.TransactionMapper;
import com.bankofbugbusters.transactionservice.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final NotificationProducer notificationProducer;

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public TransactionServiceImpl(TransactionRepository repository, TransactionMapper mapper, NotificationProducer notificationProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public TransactionResponse createTransaction(CreateTransactionRequest request) {
        log.info("Creating transaction: {}", request);
        Transaction saved = repository.save(mapper.toEntity(request));
        // 🔁 Notify via Kafka
        NotificationRequest notification = new NotificationRequest(
                "customer@example.com", // Replace with real logic
                "Transaction Successful",
                "Your transaction of " + request.amount() + " was successful."
        );
        notificationProducer.send(notification);

        return mapper.toResponse(saved);
    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        log.info("Fetching transaction with ID: {}", id);
        Transaction txn = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return mapper.toResponse(txn);
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        log.info("Fetching all transactions");
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public TransactionResponse updateTransaction(Long id, CreateTransactionRequest request) {
        log.info("Updating transaction with id: {}", id);
        Transaction txn = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        txn.setFromAccountId(request.fromAccountId());
        txn.setToAccountId(request.toAccountId());
        txn.setAmount(request.amount());
        txn.setTransactionType(request.transactionType());
        txn.setTransactionDate(java.time.LocalDateTime.now());

        Transaction updated = repository.save(txn);
        return mapper.toResponse(updated);
    }


    @Override
    public void deleteTransaction(Long id) {
        log.info("Deleting transaction with id: {}", id);
        Transaction txn = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        repository.delete(txn);
    }

}
