package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Listener;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = "ThymeleafApp", groupId = "group1")
    void thymeleafAppListener(String data){
        System.out.println("Listened this data: " + data);
    }
}
