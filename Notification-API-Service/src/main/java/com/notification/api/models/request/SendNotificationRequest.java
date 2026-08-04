package com.notification.api.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

@Data
public class SendNotificationRequest {


    @NotBlank(message = "Template Id is Required...")
    private String templateId;

    private Map<String, Object> dynamicVariables;

    private NotificationType notificationType;

}
