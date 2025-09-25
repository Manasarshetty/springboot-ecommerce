package springboot_ecommerce.project.e_commerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springboot_ecommerce.project.e_commerce.model.Product;
import springboot_ecommerce.project.e_commerce.repository.ProductRepository;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ProductRepository productRepository){
		return args -> {
			System.out.println("Adding initial products to the database...");
			productRepository.save(new Product("Laptop", "High-performance laptop for professionals", 1200.00));
			productRepository.save(new Product("Smartphone", "Latest model with a stunning camera", 800.00));
			productRepository.save(new Product("Headphones", "Noise-cancelling over-ear headphones", 250.00));
			System.out.println("Initial products added successfully.");
		};
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
