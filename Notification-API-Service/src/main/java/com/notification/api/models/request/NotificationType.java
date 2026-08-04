package com.notification.api.models.request;

public enum NotificationType {

    SMS("SMS"),
    EMAIL("EMAIL"),
    WEBHOOK("WEBHOOK");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
