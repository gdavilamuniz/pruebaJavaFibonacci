package fibonacci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fibonacci.service.FibonacciService;


@RestController
@RequestMapping("/sucesionfibonacci")
public class FibonacciController {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> sinParametros() {
        return ResponseEntity.badRequest().body("El parámetro no puede estar vacío");
    }

    @GetMapping("/{n}")
    public ResponseEntity<Object> getFibonacci(@PathVariable String n) {

    	try {
    		 
    		int number = Integer.parseInt(n);		
	        Long fibonacciValue = fibonacciService.getNFibonacci(n);
	        return ResponseEntity.ok(fibonacciValue);
	        
    	} catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("El parámetro debe ser un número positivo");
        }
    }
}