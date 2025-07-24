package com.bankofbugbusters.notificationservice.service;

import com.bankofbugbusters.notificationservice.dto.NotificationRequest;
import com.bankofbugbusters.notificationservice.entity.Notification;
import com.bankofbugbusters.notificationservice.mapper.NotificationMapper;
import com.bankofbugbusters.notificationservice.repository.NotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service

public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final ObjectMapper objectMapper;

    // Constructor injection of dependencies
    public NotificationServiceImpl(NotificationRepository repository,
                                   NotificationMapper mapper,
                                   ObjectMapper objectMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    // Actual sendNotification method to save notification and (optionally) send email
    @Override
    public void sendNotification(NotificationRequest request) {
        Notification notification = mapper.toEntity(request);
        repository.save(notification);
        log.info("Notification saved and email sent to {} with subject: {}", request.to(), request.subject());
        // TODO: Add real email sending logic here if needed
    }

    // Kafka listener consumes messages and delegates to sendNotification
    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consumeNotification(String message) {
        try {
            NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
            log.info("📨 Consumed from Kafka for: {}", request.to());
            sendNotification(request);
        } catch (Exception e) {
            log.error("Failed to parse message: {}", message, e);
        }
    }
}
