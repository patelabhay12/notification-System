package com.notification.api.dao.impls;

import com.notification.api.dao.interfaces.CacheService;
import com.notification.api.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

import static com.notification.api.constants.ApplicationConstants.TEMPLATE_REDIS_PREFIX;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public <T> void putById(final String tenantId, String id, T value) {
        put(getTemplateIdHash(tenantId), "BY_ID".concat(id), value);
    }

    @Override
    public <T> void putByName(final String tenantId, String name, T value) {
        put(getTemplateIdHash(tenantId), "BY_NAME".concat(name), value);
    }

    @Override
    public void deleteById(final String tenantId, final String id) {
        delete(getTemplateIdHash(tenantId), "BY_ID".concat(id));
    }

    @Override
    public void deleteByName(final String tenantId, final String name) {
        delete(getTemplateIdHash(tenantId), "BY_NAME".concat(name));
    }

    @Override
    public <T> Optional<T> getById(final String tenantId, final String id, Class<T> clazz) {
        return get(getTemplateIdHash(tenantId), "BY_ID".concat(id), clazz);
    }

    @Override
    public <T> Optional<T> getByName(final String tenantId, final String name, Class<T> clazz) {
        return get(getTemplateIdHash(tenantId), "BY_NAME".concat(name), clazz);
    }


    private <T> Optional<T> get(final String tenantId, final String id, Class<T> tClass) {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        String json = ops.get(getTemplateIdHash(tenantId), id).toString();
        try {
            return Optional.of(objectMapper.readValue(json, tClass));
        } catch (Exception e) {
            throw new ValidationException("Error While fetching the data from the Redis...", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private <T> void put(final String tenantId, String hashKey, T data) {
        String json = objectMapper.writeValueAsString(data);
        redisTemplate.opsForHash().put(tenantId, hashKey, json);
    }


    private void delete(final String tenantId, final String hashKey) {
        redisTemplate.opsForHash().delete(tenantId, hashKey);
    }


    private String getTemplateIdHash(String templateId) {
        return TEMPLATE_REDIS_PREFIX.concat(templateId);
    }

}
