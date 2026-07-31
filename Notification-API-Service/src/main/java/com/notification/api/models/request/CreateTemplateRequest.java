package com.notification.api.models.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

@Data
public class CreateTemplateRequest {

    @NotBlank(message = "Name Field is required")
    private String name;

    Map<String ,String> templatesVariables;

    @NotBlank(message = "message Template Field is required")
    private String messageTemplate;

}
