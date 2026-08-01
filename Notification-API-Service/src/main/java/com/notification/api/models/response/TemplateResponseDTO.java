package com.notification.api.models.response;


import com.notification.api.models.entities.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class TemplateResponseDTO {

    private String id;

    private String name;

    Map<String, String> templatesVariables;

    public TemplateResponseDTO(Template template) {
        setId(String.valueOf(template.getId()));
        setName(template.getName());
        setTemplatesVariables(template.getTemplatesVariables());
    }
}
