package com.h3c.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAuthenticApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaAuthenticApplication.class, args);
	}
}
