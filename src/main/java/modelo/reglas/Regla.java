package modelo.reglas;

import modelo.actuadores.Actuador;
import modelo.sensores.Sensor;

import java.util.List;

/**
 * Interfaz común para todas las reglas automáticas del sistema.
 * Representa el patrón Strategy: cada regla encapsula una estrategia distinta.
 */
public interface Regla {

    String getNombre();

    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
}