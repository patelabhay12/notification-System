package com.notification.api.services.interfaces;


import com.notification.api.models.request.SendNotificationRequest;
import org.springframework.stereotype.Service;

public interface NotificationService {

    void  sendNotification(SendNotificationRequest notificationRequest);
}
