package com.notification.api.pubsub.primary;

public interface GenericProvider {

    boolean sendNotification(String topic, String message);
}
