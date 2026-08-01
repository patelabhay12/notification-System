package com.notification.api.models.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
public class BaseTemplateResponse <I,R extends Number>{

    private List<I> data;
    private boolean hasMoreElement;
    public R totalCount;
}
