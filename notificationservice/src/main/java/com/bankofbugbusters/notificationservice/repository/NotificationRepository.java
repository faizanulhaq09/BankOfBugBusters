package com.bankofbugbusters.notificationservice.repository;

import com.bankofbugbusters.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
