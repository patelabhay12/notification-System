package com.notification.api.services.interfaces;

import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.response.TemplateResponse;

import java.lang.reflect.InvocationTargetException;


public interface TemplateService {

    TemplateResponse createTemplate(CreateTemplateRequest templateRequest);

    Object filterTemplate(TemplateFilterRequest filterRequest) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
