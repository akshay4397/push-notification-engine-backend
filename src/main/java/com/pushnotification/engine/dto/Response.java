package com.pushnotification.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class Response {

    @JsonProperty(value = "message")
    private String message;

}
