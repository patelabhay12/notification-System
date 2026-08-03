package com.notification.api.dao.repositories;

import com.notification.api.models.entities.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;


public interface TemplateRepository extends MongoRepository<Template, UUID>{

    Optional<Template> findByTenantIdAndId(UUID tenantId, UUID id);

    Optional<Template> findByNameIgnoreCaseAndTenantId(String name, UUID tenantId);

}
