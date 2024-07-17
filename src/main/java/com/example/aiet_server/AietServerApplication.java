package com.example.aiet_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AietServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AietServerApplication.class, args);
	}
}
