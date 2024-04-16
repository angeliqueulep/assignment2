package com.humber.edgeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class EdgeServerApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EdgeServerApplication.class);
		app.setDefaultProperties(Collections.singletonMap("spring.application.name", "edge-server"));
		app.run(args);
	}

}
