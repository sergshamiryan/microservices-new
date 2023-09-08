package com.example.notificationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;


@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class, args);

    }

    @KafkaListener(topics = "notificationType")
    public void handleNotification(Map<String, Object> orderPlacedEvent) {
        //email notification
        log.info("Received Notification For Order - {} ", orderPlacedEvent.get("orderNumber"));
    }

}
