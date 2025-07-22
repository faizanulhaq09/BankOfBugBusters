package com.bankofbugbusters.transactionservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse (
        Long id,
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount,
        String transactionType,
        LocalDateTime transactionDate
)
{}
