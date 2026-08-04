package com.notification.api.pubsub.impl;

import com.notification.api.pubsub.interfaces.RabbitMqProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(value = "messaging.fallback.rabbitMq.enable",havingValue = "true")
class RabbitMqProviderImpl implements RabbitMqProvider {
    @Override
    public boolean sendNotification(String topic, String message) {

        log.info("Sending notification to topic using RabbitMQ... for Topic: {} for message: {}", topic, message);
        return false;
    }
}
