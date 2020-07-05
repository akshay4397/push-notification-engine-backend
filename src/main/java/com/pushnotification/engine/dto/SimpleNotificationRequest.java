package com.pushnotification.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import lombok.Getter;

@Getter
public class SimpleNotificationRequest {

    @NotNull
    @JsonProperty(value = "notification_message")
    private String notificationMessage;

}
