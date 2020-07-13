package com.pushnotification.engine.kafka.producer;

import com.pushnotification.engine.kafka.config.NotificationKafkaConfig;
import com.pushnotification.engine.kafka.constant.KafkaConstants;
import com.pushnotification.engine.model.NotificationFormat;
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
    private NotificationKafkaConfig notificationKafkaConfig;

    private KafkaProducer<String, NotificationFormat> notificationProducer;

    private void initiateProducer() {
        if (this.notificationProducer == null) {
            Properties producerProperties = notificationKafkaConfig.getProducerConfig();
            this.notificationProducer = new KafkaProducer<>(producerProperties);
        }
    }

    public void generateNotification(String message) {

        initiateProducer();

        NotificationFormat notificationFormat = new NotificationFormat();
        notificationFormat.setMessage(message);
        notificationFormat.setTimestamp(System.currentTimeMillis());

        ProducerRecord<String, NotificationFormat> notification = new ProducerRecord<>(KafkaConstants.NOTIFICATION_TOPIC, notificationFormat);

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
