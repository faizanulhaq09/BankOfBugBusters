package com.bankofbugbusters.transactionservice.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransactionRequest (
        @NotNull Long fromAccountId,
        @NotNull Long toAccountId,
        @NotNull BigDecimal amount,
        @NotNull String transactionType // e.g., TRANSFER, DEPOSIT, WITHDRAW
) {}
