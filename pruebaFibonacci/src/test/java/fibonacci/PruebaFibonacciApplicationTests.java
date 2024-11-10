package fibonacci;
	
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fibonacci.models.Fibonacci;
import fibonacci.repository.FibonacciRepository;
import fibonacci.service.FibonacciService;
	
	
	@SpringBootTest
	class PruebaFibonacciApplicationTests {
	
    @Autowired
    private FibonacciService fibonacciService;
    @Autowired
    private FibonacciRepository fibonacciRepository;

    @Test
    void testBasicos() {
        assertEquals(0, fibonacciService.getNFibonacci("0"), "Fibonacci posicion 0 es 0");
        assertEquals(1, fibonacciService.getNFibonacci("1"), "Fibonacci posicion 1 es 1");
        assertEquals(3, fibonacciService.getNFibonacci("4"), "Fibonacci posicion 4 es 3");
        assertEquals(13, fibonacciService.getNFibonacci("7"), "Fibonacci posicion 7 es 13");
        assertEquals(34, fibonacciService.getNFibonacci("9"), "Fibonacci posicion 9 es 34");
        assertEquals(144, fibonacciService.getNFibonacci("12"), "Fibonacci posicion 12 es 144");
    }
    
    @Test
    void testNumeroLargo() {
        
        assertEquals(832040, fibonacciService.getNFibonacci("30"), "El número Fibonacci de 30 debe ser 832040");
        assertEquals(102334155, fibonacciService.getNFibonacci("40"), "Fibonacci posicion 40 es 102334155");
    }
    
    @Test
    void testPersistidoBase() { 	
    
 		fibonacciService.getNFibonacci("2");
 		Optional<Fibonacci> result = fibonacciRepository.findById(2);
 		if (result.isPresent()) {
	        Fibonacci fibonacci = result.get();  
	        assertEquals(1, fibonacci.getValor(), "El valor debe ser 1");
	        assertEquals(2, fibonacci.getN(), "El número debe ser 1");
	    } else {
	        fail("Deber'ia contener un valor");
	    }
    }
    
    @Test
    void numeroNegativo() {
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.getNFibonacci("-1");
        });
        assertEquals("El parámetro debe ser un número positivo", exception.getMessage());
    }
}
