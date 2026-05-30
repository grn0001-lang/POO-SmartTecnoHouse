package modelo.sensores;

import java.util.Random;

/**
 * Sensor que simula la detección de presencia en una estancia.
 */
public class SensorPresencia extends Sensor {

    private static final Random RANDOM = new Random();
    private boolean presenciaDetectada;

    public SensorPresencia(String id, String nombre, String ubicacion) {
        super(id, nombre, ubicacion, "presencia");
        this.presenciaDetectada = false;
    }

    public boolean isPresenciaDetectada() {
        return presenciaDetectada;
    }

    @Override
    public void leerValor() {
        this.presenciaDetectada = RANDOM.nextBoolean();
        setValor(presenciaDetectada ? 1 : 0);
    }

    @Override
    public String getEstado() {
        if (!isActivo()) {
            return "Sensor de presencia inactivo";
        }

        return getNombre() + ": " + (presenciaDetectada ? "presencia detectada" : "sin presencia");
    }
}