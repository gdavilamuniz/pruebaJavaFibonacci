package fibonacci.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fibonacci")
public class Fibonacci {

    @Id
    private Integer n; 
    
    private Long valor;

    public Fibonacci() {
    	
    }

    public Fibonacci(Integer n, Long valor) {
        this.n = n;
        this.valor = valor;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Long getValor() {   
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}