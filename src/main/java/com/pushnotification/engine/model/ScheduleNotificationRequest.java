package com.pushnotification.engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ScheduleNotificationRequest {

    @JsonProperty(value = "notification_message")
    private String notificationMessage;

    @JsonProperty(value = "time_interval")
    private Long timeInterval;

}
