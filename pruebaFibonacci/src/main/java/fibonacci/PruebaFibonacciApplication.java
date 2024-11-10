package fibonacci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "fibonacci.models") 
public class PruebaFibonacciApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaFibonacciApplication.class, args);
	}

}
