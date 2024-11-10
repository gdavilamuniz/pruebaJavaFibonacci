package fibonacci.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fibonacci.models.Fibonacci;
import fibonacci.models.Statistics;
import fibonacci.repository.FibonacciRepository;
import fibonacci.repository.StatisticsRepository;

@Service
public class FibonacciService {

	@Autowired
    private final FibonacciRepository fibonacciRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;
    
    //esto se hace para evitar recalcular
    private Map<Integer, Long> mapCalculados = new HashMap<>();
    
    public FibonacciService(FibonacciRepository fibonacciRepository) {
        this.fibonacciRepository = fibonacciRepository;
    }

    public Long getNFibonacci(String n) {
    
    	int number = Integer.parseInt(n);
    	if (number < 0) {
            throw new IllegalArgumentException("El parámetro debe ser un número positivo");
        }
    	
		incrementStatistics(number);
    	 
		if (mapCalculados.containsKey(n)) {
            return mapCalculados.get(n);
        }
		long fibonacciValue = calculateFibonacci(number);
		
        for (int i = 0; i <= number; i++) {
        	if(mapCalculados.get(i) != null)
            fibonacciRepository.save(new Fibonacci(i, mapCalculados.get(i)));
        }
		
        return fibonacciValue;
    }

    private long calculateFibonacci(int n) {

        if (n == 0) {
            mapCalculados.put(n, 0L); 
            return 0L;
        }
        if (n == 1) {
            mapCalculados.put(n, 1L);
            return 1L;
        }
    	
    	 long a = mapCalculados.containsKey(n - 1) ? mapCalculados.get(n - 1) : calculateFibonacci(n - 1);
         long b = mapCalculados.containsKey(n - 2) ? mapCalculados.get(n - 2) : calculateFibonacci(n - 2);
         
         long result = a + b;
         mapCalculados.put(n, result);
         
         return result;
    }
    
    private void incrementStatistics(int n) {
        Statistics stats = statisticsRepository.findById(n)
                .orElse(new Statistics(n, 0));
        stats.setCount(stats.getCount() + 1);
        statisticsRepository.save(stats);
    }
}