package com.bankofbugbusters.notificationservice.mapper;

import com.bankofbugbusters.notificationservice.dto.NotificationRequest;
import com.bankofbugbusters.notificationservice.dto.NotificationResponse;
import com.bankofbugbusters.notificationservice.entity.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public Notification toEntity(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setToEmail(request.to());
        notification.setSubject(request.subject());
        notification.setMessage(request.message());
        notification.setSentAt(LocalDateTime.now());
        return notification;
    }

    public NotificationResponse toResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getToEmail(),
                notification.getSubject(),
                notification.getMessage(),
                notification.getSentAt()
        );
    }
}
