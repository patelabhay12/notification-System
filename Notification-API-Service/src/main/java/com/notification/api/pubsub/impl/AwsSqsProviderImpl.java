package com.notification.api.pubsub.impl;

import com.notification.api.pubsub.interfaces.AwsSqsProvider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(value = "messaging.provider.aws.enable",havingValue = "true")
class AwsSqsProviderImpl implements AwsSqsProvider {

    @Override
    public boolean sendNotification(String topic, String message) {
        log.info("Sending notification using aws Sqs to topic {}", topic);
        return false;
    }
}
