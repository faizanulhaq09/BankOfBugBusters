package com.bankofbugbusters.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountRequest (
        @NotNull Long customerId,
        @NotBlank String accountType,
        @NotNull BigDecimal balance
) {}
