package com.bankofbugbusters.account.repository;

import com.bankofbugbusters.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
