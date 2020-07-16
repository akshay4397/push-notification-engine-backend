package com.pushnotification.engine.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class NotificationServiceTest {

    //@Autowired
    private NotificationService notificationService;

    //@Test
    public void startAScheduledJobTest() throws InterruptedException {
        notificationService.startAScheduledJob("Hello scheduled", 1L);

        notificationService.generateNotification("hello");
        Thread.sleep(109999);
    }

}
