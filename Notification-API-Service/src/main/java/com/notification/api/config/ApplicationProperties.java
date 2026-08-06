package com.notification.api.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApplicationProperties {

    @Value("{kafka.ingest.topic}")
    private String ingestTopic;

    @Value("{kafka.audit.topic}")
    private String auditTopic;

}
