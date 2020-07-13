package com.pushnotification.engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import lombok.Getter;

@Getter
public class ScheduleNotificationRequest {

    @NotNull
    @JsonProperty(value = "notification_message")
    private String notificationMessage;

    @NotNull
    @JsonProperty(value = "time_interval")
    private Long timeInterval;

}
