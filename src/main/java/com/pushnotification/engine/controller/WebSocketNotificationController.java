package com.pushnotification.engine.controller;

import com.pushnotification.engine.model.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class WebSocketNotificationController {

    @MessageMapping("/get_notifications")
    @SendTo("/topic/new_notifications")
    public Response getNotifications() {
        return new Response("Test response");
    }

    @ExceptionHandler
    @SendTo("/topic/errors")
    public Response exceptionHandler() {
        return new Response("Error processing request");
    }

}
