package com.bankofbugbusters.notificationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NotificationRequest (
        @Email String to,
        @NotBlank String subject,
        @NotBlank String message

){}


