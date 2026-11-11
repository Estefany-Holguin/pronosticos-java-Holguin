package pronosticos.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pronosticos.CalculadoraPronosticos;

class MapeTest {

    private static final double TOLERANCIA = 0.01;

    private CalculadoraPronosticos calculadora;

    @BeforeEach
    void prepararCalculadora() {
        calculadora = new CalculadoraPronosticos();
    }

    @Test
    @DisplayName("Calcula el MAPE normalmente")
    void calculaElMapeNormalmente() {
        double resultado = calculadora.mape(
                List.of(141.0, 138.0, 150.0),
                List.of(126.67, 133.67, 135.67));

        assertEquals(7.62, resultado, TOLERANCIA);
    }

    @Test
    @DisplayName("Excluye del MAPE los periodos con demanda real cero")
    void excluyePeriodosConDemandaCero() {
        double resultado = calculadora.mape(
                List.of(80.0, 0.0, 95.0),
                List.of(80.0, 0.0, 100.0));

        assertEquals(2.63, resultado, TOLERANCIA);
    }

    @Test
    @DisplayName("Rechaza una serie sin periodos válidos")
    void rechazaUnaSerieSinPeriodosValidos() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.mape(
                        List.of(0.0, 0.0),
                        List.of(10.0, 20.0)));
    }
}
