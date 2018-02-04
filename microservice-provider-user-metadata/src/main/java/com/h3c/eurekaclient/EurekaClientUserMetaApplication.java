package com.h3c.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientUserMetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientUserMetaApplication.class, args);
	}

}
