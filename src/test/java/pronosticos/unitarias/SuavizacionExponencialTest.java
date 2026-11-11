package pronosticos.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import pronosticos.CalculadoraPronosticos;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.provider.ValueSource;

class SuavizacionExponencialTest {

    private static final List<Double> SERIE = List.of(120.0, 132.0, 128.0, 141.0, 138.0, 150.0);

    @Test
    @DisplayName("Pronostica el siguiente periodo con alfa 0.3")
    void pronosticaElSiguientePeriodoConAlfa03() {
        double resultado = new CalculadoraPronosticos().suavizacionExponencial(SERIE, 0.3);

        assertEquals(137.5546, resultado, 0.01);
    }
    @Test
    @DisplayName("Con alfa 0 el pronostico no cambia")
    void rechazaAlfasFueraDelRango(){
        double resultado = new CalculadoraPronosticos().suavizacionExponencial(SERIE, 0.0);
        assertEquals(120.0, resultado, 0.01);
    }
    @Test
    @DisplayName("Con alfa 1 el pronostico es la ultima demanda")
    void conAlfa1ElPronosticoEsLaUltimaDemanda() {
        double resultado = new CalculadoraPronosticos().suavizacionExponencial(SERIE, 1.0);
        assertEquals(150.0, resultado, 0.01);
    }
    @ParameterizedTest
    @ValueSource(doubles = {-0.1, 1.5})
    @DisplayName("Rechaza alfas fuera del rango [0, 1]")
    void rechazaAlfasFueraDelRango(double alfa) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CalculadoraPronosticos().suavizacionExponencial(SERIE, alfa);
        });
        assertEquals("Alfa debe estar en el rango [0, 1]", exception.getMessage());
    }
}
