package com.pushnotification.engine.jobs;

import com.pushnotification.engine.kafka.producer.NotificationProducer;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

@Getter
@Setter
public class RemainderJob extends TimerTask {

    private Logger logger = LoggerFactory.getLogger(RemainderJob.class);

    private String message;
    private NotificationProducer notificationProducer;

    public RemainderJob(String message, NotificationProducer notificationProducer) {
        this.message = message;
        this.notificationProducer = notificationProducer;
    }

    @Override
    public void run() {
        notificationProducer.generateNotification(message);
    }
}
