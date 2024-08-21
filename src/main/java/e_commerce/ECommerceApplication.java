package e_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"e_commerce.configuration",
				"e_commerce.infrastructure.controllers"
		}
)
public class ECommerceApplication {
	public static void main(String[] args) {

		SpringApplication.run(ECommerceApplication.class, args);
	}
}
