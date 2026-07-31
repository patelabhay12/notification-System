package com.notification.api.services.interfaces;

import com.notification.api.models.request.CreateTemplateRequest;


public interface TemplateService {

    void createTemplate(CreateTemplateRequest templateRequest);
}
