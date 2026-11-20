package com.fitness.aiservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class AiservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiservicesApplication.class, args);
	}

}
