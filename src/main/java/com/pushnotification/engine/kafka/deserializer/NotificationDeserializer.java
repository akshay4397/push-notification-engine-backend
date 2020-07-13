package com.pushnotification.engine.kafka.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pushnotification.engine.model.NotificationFormat;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NotificationDeserializer implements Deserializer<NotificationFormat>  {

    private final Logger logger = LoggerFactory.getLogger(NotificationDeserializer.class);

    @Override
    public NotificationFormat deserialize(String s, byte[] bytes) {

        NotificationFormat notification = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            notification = objectMapper.readValue(bytes, NotificationFormat.class);
        } catch (IOException e) {
            logger.error("Failed to deserialize notification. : " + e.getMessage());
        }

        return notification;
    }

}
