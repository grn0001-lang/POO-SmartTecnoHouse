package modelo.sensores;

import java.util.Random;

/**
 * Sensor que simula la medición de temperatura de una estancia.
 */
public class SensorTemperatura extends Sensor {

    private static final Random RANDOM = new Random();

    public SensorTemperatura(String id, String nombre, String ubicacion) {
        super(id, nombre, ubicacion, "ºC");
    }

    @Override
    public void leerValor() {
        double temperatura = 18 + (RANDOM.nextDouble() * 12);
        setValor(Math.round(temperatura * 10.0) / 10.0);
    }
}
