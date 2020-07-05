package com.pushnotification.engine.kafka.producer;

import com.pushnotification.engine.kafka.config.NotificationProducerConfig;
import com.pushnotification.engine.kafka.constant.ProducerConstants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class NotificationProducer {

    private final Logger logger = LoggerFactory.getLogger(NotificationProducer.class);

    @Autowired
    private NotificationProducerConfig notificationProducerConfig;

    private KafkaProducer<String, String> notificationProducer;

    private void initiateProducer() {
        if (this.notificationProducer == null) {
            Properties producerProperties = notificationProducerConfig.getProducerConfig();
            this.notificationProducer = new KafkaProducer<>(producerProperties);
        }
    }

    public void generateNotification(String message) {

        initiateProducer();

        ProducerRecord<String, String> notification = new ProducerRecord<>(ProducerConstants.NOTIFICATION_TOPIC, message);

        notificationProducer.send(notification, (recordMetadata, e) -> {
            if (e != null) {
                logger.error("Notification generation failed :(");
            } else {
                logger.info("Notification generated successfully :) pushed to topic : " + recordMetadata.topic());
            }
        });

        notificationProducer.flush();
    }

    public void closeProducer() {
        notificationProducer.close();
    }

}
