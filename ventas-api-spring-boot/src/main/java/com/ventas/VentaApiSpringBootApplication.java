package com.ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ventas"})
public class VentaApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VentaApiSpringBootApplication.class, args);
	}
	

}
