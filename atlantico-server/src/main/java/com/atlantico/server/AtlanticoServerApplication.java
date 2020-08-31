package com.atlantico.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AtlanticoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlanticoServerApplication.class, args);
	}

}
