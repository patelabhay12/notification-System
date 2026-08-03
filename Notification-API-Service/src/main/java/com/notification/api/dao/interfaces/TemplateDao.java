package com.notification.api.dao.interfaces;

import com.notification.api.models.entities.Template;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

public interface TemplateDao {

    Optional<Template> findByTenantIdAndName(String tenantId, String name);

    Template save(Template template);

    Page<Template> filterTemplate(final Example<Template> example,final PageRequest pageRequest);

    void deleteTemplate(UUID id);

    Optional<Template> findByTenantIdAndId(UUID uuid, UUID uuid1);
}
