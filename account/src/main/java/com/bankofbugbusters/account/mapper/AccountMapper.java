package com.bankofbugbusters.account.mapper;

import com.bankofbugbusters.account.dto.AccountResponse;
import com.bankofbugbusters.account.dto.CreateAccountRequest;
import com.bankofbugbusters.account.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toEntity(CreateAccountRequest request) {
        Account account = new Account();
        account.setCustomerId(request.customerId());
        account.setAccountType(request.accountType());
        account.setBalance(request.balance());
        return account;
    }

    public AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getBalance()
        );
    }
}
