package com.bankofbugbusters.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest (
@NotBlank String fullName,
@Email
String email,
@NotBlank String phoneNumber,
@NotBlank String address,
@NotBlank
String nationalId
)
{}
