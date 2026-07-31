package com.notification.api.dao.repositories;

import com.notification.api.models.entities.Template;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TemplateRepository extends MongoRepository<Template, Long> {
}
