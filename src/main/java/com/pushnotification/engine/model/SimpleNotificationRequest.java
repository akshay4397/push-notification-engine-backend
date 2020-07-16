package com.pushnotification.engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SimpleNotificationRequest {

    @JsonProperty(value = "notification_message")
    private String notificationMessage;

}
