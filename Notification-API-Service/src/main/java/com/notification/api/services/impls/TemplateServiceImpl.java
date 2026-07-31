package com.notification.api.services.impls;

import com.notification.api.services.interfaces.TemplateService;
import com.notification.api.models.request.CreateTemplateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
class TemplateServiceImpl implements TemplateService {


    @Override
    public void createTemplate(CreateTemplateRequest templateRequest) {

    }
}
