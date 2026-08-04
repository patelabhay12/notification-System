package com.notification.api.controller.notification;


import com.notification.api.models.request.SendNotificationRequest;
import com.notification.api.services.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {


    private final NotificationService notificationService;


    @PostMapping
    public ResponseEntity sendNotification(@RequestBody SendNotificationRequest notificationRequest){
        notificationService.sendNotification(notificationRequest);

        return ResponseEntity.ok().body("Notification sent successfully");
    }

}
