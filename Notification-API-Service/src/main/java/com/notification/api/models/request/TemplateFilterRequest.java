package com.notification.api.models.request;

import com.notification.api.models.entities.Template;

public class TemplateFilterRequest extends BaseSearchDTO{
    private String name;

    @Override
    public Class getEntity() {
        return Template.class;
    }
}
