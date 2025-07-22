package com.bankofbugbusters.notificationservice.service;

import com.bankofbugbusters.notificationservice.dto.NotificationRequest;

public interface NotificationService {
    void sendNotification(NotificationRequest request);
}
