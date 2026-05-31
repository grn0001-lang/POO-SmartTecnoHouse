package modelo.sensores;

import java.util.Random;

/**
 * Sensor que simula la cantidad de luz ambiental.
 */
public class SensorLuz extends Sensor {

    private static final Random RANDOM = new Random();

    public SensorLuz(String id, String nombre, String ubicacion) {
        super(id, nombre, ubicacion, "lux");
    }

    @Override
    public void leerValor() {
        double luz = RANDOM.nextDouble() * 1000;
        setValor(Math.round(luz * 10.0) / 10.0);
    }
}