package com.pushnotification.engine;

import com.pushnotification.engine.kafka.consumer.NotificationConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PushNotificationEngineApplication implements CommandLineRunner {

	@Autowired
	private NotificationConsumer notificationConsumer;

	public static void main(String[] args) {
		SpringApplication.run(PushNotificationEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		notificationConsumer.startConsumer();
	}
}
