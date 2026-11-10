package pronosticos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Punto de entrada de la aplicación Spring Boot.
 * Se levanta con: mvn spring-boot:run
 */
@SpringBootApplication
public class PronosticosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PronosticosApplication.class, args);
    }

    /**
     * Registra la calculadora como un bean de Spring para inyectarla en el controlador.
     * La clase en sí no conoce Spring, y por eso sus pruebas unitarias la crean con new.
     */
    @Bean
    CalculadoraPronosticos calculadoraPronosticos() {
        return new CalculadoraPronosticos();
    }
}
