package com.pushnotification.engine.controller;

import com.pushnotification.engine.model.Response;
import com.pushnotification.engine.model.ScheduleNotificationRequest;
import com.pushnotification.engine.model.SimpleNotificationRequest;
import com.pushnotification.engine.service.NotificationService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("notifications")
public class NotificationSchedulerController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/schedule")
    public Response scheduleNotifications(@RequestBody @NotNull ScheduleNotificationRequest scheduleNotificationRequest) {
        String notificationMessage = scheduleNotificationRequest.getNotificationMessage();
        Long timeInterval = scheduleNotificationRequest.getTimeInterval();

        notificationService.startAScheduledJob(notificationMessage, timeInterval);

        return new Response("Periodic notification scheduled");
    }

    @PostMapping("/generate")
    public Response generateNotification(@RequestBody @NotNull SimpleNotificationRequest simpleNotificationRequest) {
        String notificationMessage = simpleNotificationRequest.getNotificationMessage();

        notificationService.generateNotification(notificationMessage);

        return new Response("Notification generated");
    }


}
