package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Controller;

import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.config.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class KafkaMessageController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @PostMapping("/{message}")
    public void publishMessage(@PathVariable("message") String message){
        template.send("ThymeleafApp", message);
    }

}
