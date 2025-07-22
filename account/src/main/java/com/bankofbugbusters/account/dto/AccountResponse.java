package com.bankofbugbusters.account.dto;

import java.math.BigDecimal;

public record AccountResponse (
        Long id,
        Long customerId,
        String accountType,
        BigDecimal balance
) {}
