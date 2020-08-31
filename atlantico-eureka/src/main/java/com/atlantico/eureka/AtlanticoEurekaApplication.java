package com.atlantico.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AtlanticoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlanticoEurekaApplication.class, args);
	}

}
