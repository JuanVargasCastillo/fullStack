package com.gestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gestion"})
public class GestionApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionApiSpringBootApplication.class, args);
	}
	

}
