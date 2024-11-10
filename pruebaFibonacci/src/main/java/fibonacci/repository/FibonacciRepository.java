package fibonacci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fibonacci.models.Fibonacci;

@Repository
public interface FibonacciRepository extends JpaRepository<Fibonacci, Integer> {
}