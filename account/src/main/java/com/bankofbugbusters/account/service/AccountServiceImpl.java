package com.bankofbugbusters.account.service;

import com.bankofbugbusters.account.dto.AccountResponse;
import com.bankofbugbusters.account.dto.CreateAccountRequest;
import com.bankofbugbusters.account.entity.Account;
import com.bankofbugbusters.account.exception.ResourceNotFoundException;
import com.bankofbugbusters.account.mapper.AccountMapper;
import com.bankofbugbusters.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AccountServiceImpl implements AccountService{


    private final AccountRepository repository;
    private final AccountMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    public AccountServiceImpl(AccountRepository repository, AccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        log.info("Creating account for customerId: {}", request.customerId());
        Account saved = repository.save(mapper.toEntity(request));
        return mapper.toResponse(saved);
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        log.info("Fetching account by id: {}", id);
        Account account = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        return mapper.toResponse(account);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        log.info("Fetching all accounts");
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponse updateAccount(Long id, CreateAccountRequest request) {
        log.info("Updating account with id: {}", id);
        Account account = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));

        account.setAccountType(request.accountType());
        account.setBalance(request.balance()); // Assume full update for simplicity

        Account updated = repository.save(account);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteAccount(Long id) {
        log.info("Deleting account with id: {}", id);
        Account account = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        repository.delete(account);
    }

}

