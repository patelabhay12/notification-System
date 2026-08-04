package com.notification.api.pubsub.impl;

import com.notification.api.pubsub.interfaces.MqtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(value = "messaging.fallback.Mqt.enable",havingValue = "true")
class MqtProviderImpl implements MqtProvider {
    @Override
    public boolean sendNotification(String topic, String message) {

        log.info("Sending notification using Mqt to topic {}", topic);
        return false;
    }
}
