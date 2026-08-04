package com.notification.api.pubsub.falllback;

public interface GenericFallBackPublisher {

    boolean sendNotification(String topic, String message);

}
