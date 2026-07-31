package com.notification.api.controller;

import com.notification.api.services.interfaces.TemplateService;
import com.notification.api.models.request.CreateTemplateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    public void createTemplate(@RequestBody CreateTemplateRequest templateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(templateService.createTemplate(templateRequest));
    }
}
