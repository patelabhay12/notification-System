package com.notification.api.models.response;

import com.notification.api.models.entities.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateResponse {
    private String id;
    private String name;


    public TemplateResponse(final Template template) {
        setId(template.getId().toString());
        setName(template.getName());
    }
}
