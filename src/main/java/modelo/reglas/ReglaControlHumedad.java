package modelo.reglas;

import modelo.actuadores.Actuador;
import modelo.actuadores.ActuadorPersiana;
import modelo.sensores.Sensor;
import modelo.sensores.SensorHumedad;

import java.util.List;

/**
 * Regla que ajusta la apertura de la persiana según la humedad.
 */
public class ReglaControlHumedad implements Regla {

    @Override
    public String getNombre() {
        return "Control de humedad";
    }

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        SensorHumedad sensorHumedad = buscarSensor(sensores, SensorHumedad.class);
        ActuadorPersiana persiana = buscarActuador(actuadores, ActuadorPersiana.class);

        if (sensorHumedad == null || persiana == null) {
            return;
        }

        double humedad = sensorHumedad.getValor();

        if (humedad >= 70) {
            persiana.setPorcentajeApertura(100);
        } else if (humedad >= 55) {
            persiana.setPorcentajeApertura(50);
        } else {
            persiana.setPorcentajeApertura(0);
        }
    }

    private <T extends Sensor> T buscarSensor(List<Sensor> sensores, Class<T> tipo) {
        for (Sensor sensor : sensores) {
            if (tipo.isInstance(sensor)) {
                return tipo.cast(sensor);
            }
        }
        return null;
    }

    private <T extends Actuador> T buscarActuador(List<Actuador> actuadores, Class<T> tipo) {
        for (Actuador actuador : actuadores) {
            if (tipo.isInstance(actuador)) {
                return tipo.cast(actuador);
            }
        }
        return null;
    }
}