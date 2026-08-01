package com.notification.api.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "templates")
public class Template extends AbstractEntity {

    @Id
    private UUID id;

    private String name;

    private Map<String, String> templatesVariables;

    private String messageTemplate;


    private UUID tenantId;
}
