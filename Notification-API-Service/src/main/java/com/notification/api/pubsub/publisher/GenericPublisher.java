package com.notification.api.pubsub.publisher;

public interface GenericPublisher {

    void sendNotificationToIngest(Object input);

    void sendNotificationToAudit(Object input);

    void sendNotification(String topic, String message);
}
