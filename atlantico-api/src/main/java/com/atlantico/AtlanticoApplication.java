package com.atlantico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableEurekaClient
public class AtlanticoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlanticoApplication.class, args);
	}

}
