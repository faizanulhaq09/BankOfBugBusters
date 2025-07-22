package com.bankofbugbusters.account.service;

import com.bankofbugbusters.account.dto.AccountResponse;
import com.bankofbugbusters.account.dto.CreateAccountRequest;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);
    AccountResponse getAccountById(Long id);
    List<AccountResponse> getAllAccounts();
    AccountResponse updateAccount(Long id, CreateAccountRequest request);
    void deleteAccount(Long id);

}
