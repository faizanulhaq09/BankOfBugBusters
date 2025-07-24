package com.bankofbugbusters.notificationservice.controller;


import com.bankofbugbusters.notificationservice.dto.NotificationRequest;
import com.bankofbugbusters.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> sendNotification(@Valid @RequestBody NotificationRequest request) {
        notificationService.sendNotification(request);
        return ResponseEntity.ok().build();
    }
}
