package com.jhuguet.ThymeleafCRUDApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ThymeleafCrudApplication {


	public static void main(String[] args) {
		SpringApplication.run(ThymeleafCrudApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
		return args -> {
			kafkaTemplate.send("ThymeleafApp", "Consumer emitted for the nth time.");
		};
		//"I am being emitted for the first time."
		//new UserServiceEvent(this, "I was emitted")
	}
}
