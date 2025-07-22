package com.bankofbugbusters.notificationservice.service;

import com.bankofbugbusters.notificationservice.dto.NotificationRequest;
import com.bankofbugbusters.notificationservice.entity.Notification;
import com.bankofbugbusters.notificationservice.mapper.NotificationMapper;
import com.bankofbugbusters.notificationservice.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service

public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);


    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public NotificationServiceImpl(NotificationRepository repository, NotificationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void sendNotification(NotificationRequest request) {
        log.info("📨 Consumed notification from Kafka for: {}", request.to());

        Notification notification = mapper.toEntity(request);
        repository.save(notification);

        // Simulate sending email (we just log it)
        log.info("✅ Email sent to {} with subject: {}", request.to(), request.subject());
    }
}
