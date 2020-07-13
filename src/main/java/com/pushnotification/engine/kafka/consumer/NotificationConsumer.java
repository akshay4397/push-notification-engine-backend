package com.pushnotification.engine.kafka.consumer;

import com.pushnotification.engine.kafka.config.NotificationKafkaConfig;
import com.pushnotification.engine.kafka.constant.KafkaConstants;
import com.pushnotification.engine.model.NotificationFormat;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class NotificationConsumer{

    private final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    @Autowired
    private NotificationKafkaConfig notificationKafkaConfig;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private KafkaConsumer<String, NotificationFormat> notificationConsumer;

    private void initiateConsumer() {
        if (notificationConsumer == null) {
            Properties consumerProperties = notificationKafkaConfig.getConsumerConfig();
            notificationConsumer = new KafkaConsumer<>(consumerProperties);
        }
    }

    public void startConsumer() {

        initiateConsumer();
        notificationConsumer.subscribe(Collections.singletonList(KafkaConstants.NOTIFICATION_TOPIC));

        logger.info("Notification consumer initialized...");

        while (true) {
            logger.info("Polling kafka for new notifications...");
            ConsumerRecords<String, NotificationFormat> notifications = notificationConsumer.poll(Duration.ofSeconds(1L));

            for (ConsumerRecord<String, NotificationFormat> notification : notifications) {
                NotificationFormat notificationFormat = notification.value();
                simpMessagingTemplate.convertAndSend("/topic/new_notifications", notificationFormat);

                logger.info("Notification pushed to topic. Notification : " + notificationFormat.toString());
            }
        }

    }

}
