package com.jhuguet.ThymeleafCRUDApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
* Access Swagger documentation: http://localhost:8080/v3/api-docs
*
* Access Swagger UI documentation: http://localhost:8080/swagger-ui/index.html
* */

@EnableWebMvc
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
