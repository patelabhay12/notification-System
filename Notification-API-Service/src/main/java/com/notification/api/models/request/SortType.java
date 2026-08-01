package com.notification.api.models.request;

public enum SortType {

    ASC("ASC"),
    DESC("DESC");

    private final String value;

    SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}