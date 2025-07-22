package com.bankofbugbusters.transactionservice.kafka;

import com.bankofbugbusters.transactionservice.dto.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {
    private final KafkaTemplate<String, NotificationRequest> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);

    public NotificationProducer(KafkaTemplate<String, NotificationRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(NotificationRequest request) {
        log.info("🔁 Sending notification to Kafka: {}", request.to());
        kafkaTemplate.send("notification-topic", request);
    }
}
