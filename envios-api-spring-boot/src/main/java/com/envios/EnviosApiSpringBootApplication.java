package  com.envios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.envios"})
public class EnviosApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnviosApiSpringBootApplication.class, args);
	}
	

}
