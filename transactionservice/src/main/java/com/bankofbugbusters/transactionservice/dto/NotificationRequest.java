package com.bankofbugbusters.transactionservice.dto;

public record NotificationRequest (
        String to,
        String subject,
        String body
){}
