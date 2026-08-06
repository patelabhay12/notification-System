package com.notification.api.dao.impls;


import com.notification.api.dao.interfaces.CacheService;
import com.notification.api.dao.interfaces.TemplateDao;
import com.notification.api.dao.repositories.TemplateRepository;
import com.notification.api.models.entities.Template;
import com.notification.api.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
class TemplateDaoImpl implements TemplateDao {


    private final TemplateRepository templateRepository;
    private final CacheService cacheService;

    @Override
    public Optional<Template> findByTenantIdAndName(final String tenantId, final String name) {
        return cacheService.getByName(tenantId, name, Template.class).or(() ->
                templateRepository.findByNameIgnoreCaseAndTenantId(name, UUID.fromString(tenantId))
                        .map((Template template) -> {
                            cacheService.putByName(tenantId, name, template);
                            return template;
                        }));
    }

    @Override
    public Template save(Template template) {
        cacheService.putById(template.getTenantId().toString(), template.getId().toString(), template);
        cacheService.putByName(template.getTenantId().toString(), template.getName(), template);
        templateRepository.save(template);
        return template;
    }

    @Override
    public Page<Template> filterTemplate(Example<Template> example, PageRequest pageRequest) {
        return templateRepository.findAll(example, pageRequest);
    }

    @Override
    public void deleteTemplate(UUID id, Supplier<? extends Exception> exceptionHandler) {

        findByTenantIdAndId(UUID.fromString(CommonUtils.getCurrentTenantId()), id)
                .ifPresentOrElse((Template template) -> {
                    cacheService.deleteById(CommonUtils.getCurrentTenantId(), id.toString());
                    cacheService.deleteByName(CommonUtils.getCurrentTenantId(), template.getName());
                }, exceptionHandler::get);


        templateRepository.deleteById(id);
    }

    @Override
    public Optional<Template> findByTenantIdAndId(UUID uuid, UUID uuid1) {
        return cacheService.getById(uuid.toString(), uuid.toString(), Template.class).or(() ->
                templateRepository.findByTenantIdAndId(uuid, uuid1).map((Template template) -> {
                    cacheService.putById(uuid.toString(), uuid.toString(), template);
                    return template;
                }));

    }
}
