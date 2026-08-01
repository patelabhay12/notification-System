package com.notification.api.models.request;


import lombok.Data;

@Data
public class SortRequest {

    private String sortKey;

    private SortType sortType;
}
