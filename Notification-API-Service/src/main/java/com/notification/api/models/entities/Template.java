package com.notification.api.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
@Document(collation = "templates")
public class Template extends AbstractEntity {

    private UUID id;

    private String name;

    Map<String ,String> templatesVariables;

    private String messageTemplate;


    private UUID tenantId;
}
