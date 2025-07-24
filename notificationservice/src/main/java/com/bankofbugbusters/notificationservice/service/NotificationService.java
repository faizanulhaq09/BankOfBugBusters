package com.bankofbugbusters.notificationservice.service;

import com.bankofbugbusters.notificationservice.dto.NotificationRequest;
import org.springframework.kafka.annotation.KafkaListener;

public interface NotificationService {
    void sendNotification(NotificationRequest request);

}
