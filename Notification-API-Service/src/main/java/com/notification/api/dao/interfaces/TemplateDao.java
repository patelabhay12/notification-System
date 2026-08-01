package com.notification.api.dao.interfaces;

import com.notification.api.models.entities.Template;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface TemplateDao {

    Optional<Template> findByTenantIdAndName(String tenantId, String name);

    void save(Template template);

    Page<Template> filterTemplate(final Example<Template> example,final PageRequest pageRequest);
}
