package com.pushnotification.engine.service;

import com.pushnotification.engine.jobs.RemainderJob;
import com.pushnotification.engine.kafka.producer.NotificationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;

@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationProducer notificationProducer;

    public void startAScheduledJob(String notificationMessage, Long timeInterval) {

        RemainderJob remainderJob = new RemainderJob(notificationMessage, notificationProducer);
        long timeIntervalInMilSec = timeInterval * 1000;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(remainderJob, 0, timeIntervalInMilSec);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down scheduled notification generator");
            notificationProducer.closeProducer();
            logger.info("Scheduled notification generator stopped");
            }
        ));

    }

    public void generateNotification(String notificationMessage) {
        notificationProducer.generateNotification(notificationMessage);
    }

}
