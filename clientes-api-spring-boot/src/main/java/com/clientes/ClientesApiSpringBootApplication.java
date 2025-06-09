package com.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.clientes"})
public class ClientesApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesApiSpringBootApplication.class, args);
	}
	

}
