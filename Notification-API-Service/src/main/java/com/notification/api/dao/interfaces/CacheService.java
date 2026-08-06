package com.notification.api.dao.interfaces;

import java.util.Optional;

public interface CacheService {
    <T> void putById(String tenantId, String id, T value);

    <T> void putByName(String tenantId, String name, T value);

    void deleteById(String tenantId, String id);

    void deleteByName(String tenantId, String name);

    <T> Optional<T> getById(String tenantId, String id, Class<T> clazz);

    <T> Optional<T> getByName(String tenantId, String name, Class<T> clazz);
}
