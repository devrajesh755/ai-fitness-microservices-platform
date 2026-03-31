package com.fitness.activityservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActivityservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityservicesApplication.class, args);
	}

}
