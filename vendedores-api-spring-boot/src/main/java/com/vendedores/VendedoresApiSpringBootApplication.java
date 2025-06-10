package com.vendedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.vendedores"})
public class VendedoresApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendedoresApiSpringBootApplication.class, args);
	}
	

}
