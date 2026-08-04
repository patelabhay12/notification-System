package com.notification.api.pubsub.publisher;

public interface GenericPublisher {
    void sendNotification(String topic, String message);
}
