package com.notification.api.controller;

import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.request.UpdateTemplateRequest;
import com.notification.api.models.response.ApiResponse;
import com.notification.api.models.response.TemplateResponse;
import com.notification.api.services.interfaces.TemplateService;
import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.utils.CommonUtils;
import com.notification.api.utils.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public ResponseEntity<TemplateResponse> createTemplate(@Valid @RequestBody CreateTemplateRequest templateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(templateService.createTemplate(templateRequest));
    }


    @GetMapping
    public ResponseEntity<Object> filterTemplate(TemplateFilterRequest filterRequest) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(templateService.filterTemplate(filterRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TemplateResponse> updateTemplate(@PathVariable String id, @Valid @RequestBody UpdateTemplateRequest updateTemplateRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(templateService.updateTemplate(id,updateTemplateRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteTemplate(@PathVariable String id) {
        templateService.deleteTemplate(UUID.fromString(id));
        return ResponseUtil.success(null,"Template Deleted successfully...").getBody();
    }

}
