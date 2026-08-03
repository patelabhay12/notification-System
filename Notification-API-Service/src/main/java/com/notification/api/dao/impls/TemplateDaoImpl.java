package com.notification.api.dao.impls;


import com.notification.api.dao.interfaces.TemplateDao;
import com.notification.api.dao.repositories.TemplateRepository;
import com.notification.api.models.entities.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
class TemplateDaoImpl implements TemplateDao {


    private final TemplateRepository templateRepository;

    @Override
    public Optional<Template> findByTenantIdAndName(final String tenantId, final String name) {

        return templateRepository.findByNameIgnoreCaseAndTenantId(name, UUID.fromString(tenantId));
    }

    @Override
    public Template save(Template template) {
        templateRepository.save(template);
        return template;
    }

    @Override
    public Page<Template> filterTemplate(Example<Template> example, PageRequest pageRequest) {
        return templateRepository.findAll(example, pageRequest);
    }

    @Override
    public void deleteTemplate(UUID id) {
        templateRepository.deleteById(id);
    }

    @Override
    public Optional<Template> findByTenantIdAndId(UUID uuid, UUID uuid1) {
        return templateRepository.findByTenantIdAndId(uuid, uuid1);
    }

}
