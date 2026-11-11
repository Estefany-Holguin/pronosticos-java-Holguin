package pronosticos.unitarias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pronosticos.CalculadoraPronosticos;

class AlertasTest {

    private CalculadoraPronosticos calculadora;

    @BeforeEach
    void prepararCalculadora() {
        calculadora = new CalculadoraPronosticos();
    }

    @Test
    @DisplayName("Genera alerta cuando el error supera el umbral")
    void generaAlertaCuandoElErrorSuperaElUmbral() {
        assertTrue(calculadora.debeGenerarAlerta(12, 10));
    }

    @Test
    @DisplayName("Genera alerta cuando el error es igual al umbral")
    void generaAlertaCuandoElErrorEsIgualAlUmbral() {
        assertTrue(calculadora.debeGenerarAlerta(10, 10));
    }

    @Test
    @DisplayName("No genera alerta cuando el error está por debajo del umbral")
    void noGeneraAlertaCuandoElErrorEstaPorDebajoDelUmbral() {
        assertFalse(calculadora.debeGenerarAlerta(7, 10));
    }

    @Test
    @DisplayName("Rechaza un umbral fuera de rango")
    void rechazaUnUmbralFueraDeRango() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.debeGenerarAlerta(10, 150));
    }
}