package com.bankofbugbusters.notificationservice.dto;

import java.time.LocalDateTime;

public record NotificationResponse (
        Long id,
        String to,
        String subject,
        String message,
        LocalDateTime sentAt
){}
