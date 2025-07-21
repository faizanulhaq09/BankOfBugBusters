package com.bankofbugbusters.customer.dto;

public record CustomerResponse (
        Long id,
        String fullName,
        String email,
        String phoneNumber,
        String address,
        String nationalId
)
{}
