package com.notification.api.models.request;

import lombok.Data;

import java.util.Map;

@Data
public class IngestTopicDTO {

    private String requestId;

    private String tenantId;

    private long receivedAt;

    private String templateId;

    private Map<String, Object> dynamicVariables;

    private NotificationType notificationType;

}
