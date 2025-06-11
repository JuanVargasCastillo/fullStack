package com.soportes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.soportes"})
public class SoportesApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoportesApiSpringBootApplication.class, args);
	}
	

}
