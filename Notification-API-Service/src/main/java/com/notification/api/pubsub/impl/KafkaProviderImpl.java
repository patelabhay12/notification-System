package com.notification.api.pubsub.impl;

import com.notification.api.pubsub.interfaces.KafkaProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "messaging.provider.Kafka.enable",havingValue = "true")
class KafkaProviderImpl implements KafkaProvider {


    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public boolean sendNotification(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info("Sent notification to topic {}", topic);
            return true;
        }catch (Exception e){
            log.error("Error while publishing data to Kafka for Topic : {} with Error : {}",topic,e.getMessage());
            return false;
        }
    }
}
