package com.pushnotification.engine.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pushnotification.engine.model.NotificationFormat;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationSerializer implements Serializer<NotificationFormat> {

    private final Logger logger = LoggerFactory.getLogger(NotificationSerializer.class);

    @Override
    public byte[] serialize(String s, NotificationFormat notificationFormat) {

        byte[] serializedNotification = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serializedNotification = objectMapper.writeValueAsBytes(notificationFormat);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize notification. :" + e.getMessage());
        }

        if (serializedNotification == null)
            serializedNotification = new byte[0];

        return serializedNotification;
    }

}
