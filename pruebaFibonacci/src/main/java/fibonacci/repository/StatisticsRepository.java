package fibonacci.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fibonacci.models.Statistics;

public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
	
}