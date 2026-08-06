package com.notification.api.services.impls;

import com.notification.api.dao.interfaces.TemplateDao;
import com.notification.api.exception.ValidationException;
import com.notification.api.models.context.NotificationContext;
import com.notification.api.models.context.NotificationContextHolder;
import com.notification.api.models.entities.Template;
import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.request.UpdateTemplateRequest;
import com.notification.api.models.response.FilterTemplateResponse;
import com.notification.api.models.response.TemplateResponse;
import com.notification.api.models.response.TemplateResponseDTO;
import com.notification.api.services.interfaces.TemplateService;
import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import static com.notification.api.constants.ErrorConstants.TEMPLATE_ALREADY_EXIT;


@Service
@Slf4j
@RequiredArgsConstructor
class TemplateServiceImpl implements TemplateService {


    private final TemplateDao templateDao;

    @Override
    public TemplateResponse createTemplate(CreateTemplateRequest templateRequest) {

        NotificationContext context = NotificationContextHolder.getContext();

        templateDao.findByTenantIdAndName(context.tenantId(), templateRequest.getName()).ifPresent((Template template) -> {
            throw new ValidationException(TEMPLATE_ALREADY_EXIT, HttpStatus.BAD_REQUEST.value());
        });


        Template template = new Template();

        template.setId(CommonUtils.generateUUID());
        template.setTenantId(UUID.fromString(context.tenantId()));
        BeanUtils.copyProperties(templateRequest, template);
        template.entityCreated();

        templateDao.save(template);
        return new TemplateResponse(template);
    }

    @Override
    public FilterTemplateResponse filterTemplate(TemplateFilterRequest filterRequest)
            throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        Page<Template> templates = templateDao.filterTemplate(
                filterRequest.buildSearch(),
                filterRequest.buildPageRequest()
        );

        List<TemplateResponseDTO> templateResponseDTOList = templates.stream()
                .map(TemplateResponseDTO::new)
                .toList();

        return new FilterTemplateResponse(
                templateResponseDTOList,
                templates.hasNext(),
                templates.getTotalElements()
        );
    }

    @Override
    public TemplateResponse updateTemplate(String id, UpdateTemplateRequest updateTemplateRequest) {


        log.info("Current tenant Id is ... " + CommonUtils.getCurrentTenantId());
        log.info("Current id is ... " + id);
        Template template = templateDao.findByTenantIdAndId(
                UUID.fromString(CommonUtils.getCurrentTenantId()),
                UUID.fromString(id)
        ).orElseThrow(() -> new ValidationException(
                "Template not found",
                HttpStatus.NOT_FOUND.value()));

        if (CommonUtils.isNotEmpty(updateTemplateRequest.getName())
                && !updateTemplateRequest.getName().trim().equals(template.getName())) {

            String name = updateTemplateRequest.getName().trim();

            templateDao.findByTenantIdAndName(CommonUtils.getCurrentTenantId(), name)
                    .ifPresent(existing -> {
                        throw new ValidationException(
                                TEMPLATE_ALREADY_EXIT,
                                HttpStatus.BAD_REQUEST.value());
                    });

            template.setName(name);
        }

        if (CommonUtils.isNotEmpty(updateTemplateRequest.getMessageTemplate())) {
            template.setMessageTemplate(updateTemplateRequest.getMessageTemplate());
        }

        if (CommonUtils.isNotEmpty(updateTemplateRequest.getTemplatesVariables())) {
            template.setTemplatesVariables(updateTemplateRequest.getTemplatesVariables());
        }

        Template savedTemplate = templateDao.save(template);

        return new TemplateResponse(savedTemplate);
    }

    @Override
    public void deleteTemplate(UUID id) {
        templateDao.findByTenantIdAndId(
                        UUID.fromString(CommonUtils.getCurrentTenantId()), id)
                .orElseThrow(() -> new ValidationException(
                        "Template not found",
                        HttpStatus.NOT_FOUND.value()
                ));
        templateDao.deleteTemplate(id, () -> new ValidationException(
                "Template not found",
                HttpStatus.NOT_FOUND.value()
        ));

    }
}
