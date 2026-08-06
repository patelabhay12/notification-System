package com.notification.api.pubsub.publisher;

import com.notification.api.config.ApplicationProperties;
import com.notification.api.exception.ValidationException;
import com.notification.api.pubsub.falllback.GenericFallBackPublisher;
import com.notification.api.pubsub.primary.GenericProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericPublisherImpl implements GenericPublisher {


    private final List<GenericProvider> genericPublishers;
    private final List<GenericFallBackPublisher> fallBackPublishers;
    private ObjectMapper objectMapper;
    private ApplicationProperties applicationProperties;


    @Override
    public void sendNotificationToIngest(Object input) {
        sendNotification(applicationProperties.getIngestTopic(), convertDataIntoString(input));
    }

    @Override
    public void sendNotificationToAudit(Object input) {
        sendNotification(applicationProperties.getAuditTopic(), convertDataIntoString(input));
    }


    @Override
    public void sendNotification(String topic, String message) {
        log.info("Sending notification using generic publisher to topic {}", topic);

        AtomicBoolean isFailure = new AtomicBoolean(false);

        genericPublishers.forEach(provider -> {
            boolean isPublished = provider.sendNotification(topic, message);

            if (!isFailure.get()) {
                isFailure.set(isPublished);
            }

            if (isPublished) {
                log.info("Published notification has been sent to topic : {} with provider : {}", topic, provider.getClass().getSimpleName());
            } else {
                log.error("Error while publishing the Notification to topic : {} with provider : {}", topic, provider.getClass().getSimpleName());
            }
        });


        fallBackPublishers.forEach(provider -> {
            if (!isFailure.get()) {
                boolean isPublished = provider.sendNotification(topic, message);
                if (!isFailure.get()) {
                    isFailure.set(isPublished);
                }
                if (isPublished) {
                    isFailure.set(true);
                    log.info("Published notification has been sent to topic : {} with fallback :{} ", topic, provider.getClass().getSimpleName());
                } else {
                    log.error("Error while publishing the Notification to topic : {} with fallback : {}", topic, provider.getClass().getSimpleName());
                }
            }
        });

    }


    private String convertDataIntoString(Object input) {
        try {
            return objectMapper.writeValueAsString(input);
        } catch (Exception e) {
            log.info("Error while converting object to string : {}", e.getMessage());

            throw new ValidationException("Error while parsing payload...", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
