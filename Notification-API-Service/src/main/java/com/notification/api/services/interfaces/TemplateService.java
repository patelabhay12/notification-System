package com.notification.api.services.interfaces;

import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.request.UpdateTemplateRequest;
import com.notification.api.models.response.TemplateResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;


public interface TemplateService {

    TemplateResponse createTemplate(CreateTemplateRequest templateRequest);

    Object filterTemplate(TemplateFilterRequest filterRequest) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    TemplateResponse updateTemplate(String id, UpdateTemplateRequest updateTemplateRequest);

    void deleteTemplate(UUID id);
}
