package pronosticos;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API HTTP del sistema de pronósticos.
 *
 * <p>El controlador solo traduce peticiones HTTP a llamadas de la calculadora.
 * Las reglas de negocio viven en {@link CalculadoraPronosticos}.
 */
@RestController
@RequestMapping("/api")
public class PronosticoController {

    private final CalculadoraPronosticos calculadora;

    // Inyección por constructor: Spring entrega la calculadora registrada como bean.
    public PronosticoController(CalculadoraPronosticos calculadora) {
        this.calculadora = calculadora;
    }

    /** Verificación de vida del servicio: responde si la aplicación está arriba. */
    @GetMapping("/salud")
    public Map<String, String> salud() {
        return Map.of("estado", "ok");
    }

    /** Calcula el MAD de dos series enviadas en el cuerpo de la petición. */
    @PostMapping("/pronosticos/mad")
    public Map<String, Double> mad(@RequestBody SeriesPeticion peticion) {
        return Map.of("mad", calculadora.mad(peticion.real(), peticion.pronostico()));
    }

    /** Convierte un dato inválido en una respuesta 400 con un mensaje claro. */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> datoInvalido(IllegalArgumentException error) {
        return ResponseEntity.badRequest().body(Map.of("error", error.getMessage()));
    }

    /** Cuerpo esperado en la petición: dos listas con la misma cantidad de periodos. */
    public record SeriesPeticion(List<Double> real, List<Double> pronostico) {
    }
}
