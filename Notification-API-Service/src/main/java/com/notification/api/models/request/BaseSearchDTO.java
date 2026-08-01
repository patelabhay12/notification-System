package com.notification.api.models.request;

import com.notification.api.utils.CommonUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static java.util.Optional.ofNullable;

public abstract class BaseSearchDTO<T> {


    private Integer page;

    private Integer size;

    private SortRequest sortRequest;


    public PageRequest buildPageRequest() {

        int page = ofNullable(this.page).orElse(0);
        int size = ofNullable(this.size).orElse(10);

        return ofNullable(sortRequest).filter(req -> CommonUtils.isNotEmpty(req.getSortKey())).filter(req -> CommonUtils.isNotEmpty(req.getSortType())).map(req -> PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(String.valueOf(req.getSortType())), req.getSortKey()))).orElse(PageRequest.of(page, size));
    }


    public Example<T> buildSearch() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class clzz = getEntity();

        T instance = (T) clzz.getDeclaredConstructor().newInstance();


        injectTenantId(instance);

        for (Field dtoFiled : this.getClass().getDeclaredFields()) {
            dtoFiled.setAccessible(true);

            Object filed = dtoFiled.get(this);
            if (filed != null) {
                Field entity = getField(clzz, dtoFiled.getName());
                entity.setAccessible(true);
                entity.set(instance, filed);
            }
        }

        ExampleMatcher macher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();


        return Example.of(instance, macher);
    }

    private void injectTenantId(T instance) throws IllegalAccessException {
        Field tenantField = getField(this.getEntity().getClass(), "tenantId");
        tenantField.setAccessible(true);
        tenantField.set(tenantField, UUID.fromString(CommonUtils.getCurrentTenantId()));
    }


    public Field getField(final Class<?> clzz, final String name) {
        Class<?> current = clzz;

        while (current != null) {
            try {
                return current.getField(name);
            } catch (NoSuchFieldException e) {
                current = current.getSuperclass();
            }
        }

        return null;
    }

    public abstract Class<T> getEntity();
}
