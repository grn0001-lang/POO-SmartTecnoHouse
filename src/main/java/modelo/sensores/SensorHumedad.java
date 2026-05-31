package modelo.sensores;

import java.util.Random;

/**
 * Sensor adicional que simula la humedad relativa de una estancia.
 */
public class SensorHumedad extends Sensor {

    private static final Random RANDOM = new Random();

    public SensorHumedad(String id, String nombre, String ubicacion) {
        super(id, nombre, ubicacion, "%");
    }

    @Override
    public void leerValor() {
        double humedad = 35 + (RANDOM.nextDouble() * 45);
        setValor(Math.round(humedad * 10.0) / 10.0);
    }
}