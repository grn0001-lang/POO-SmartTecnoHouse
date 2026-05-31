package modelo.reglas;

import modelo.actuadores.Actuador;
import modelo.actuadores.ActuadorVentilador;
import modelo.sensores.Sensor;
import modelo.sensores.SensorTemperatura;

import java.util.List;

/**
 * Regla que ajusta la velocidad del ventilador según la temperatura.
 */
public class ReglaVentilacionConfortable implements Regla {

    @Override
    public String getNombre() {
        return "Ventilación confortable";
    }

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        SensorTemperatura sensorTemperatura = buscarSensor(sensores, SensorTemperatura.class);
        ActuadorVentilador ventilador = buscarActuador(actuadores, ActuadorVentilador.class);

        if (sensorTemperatura == null || ventilador == null) {
            return;
        }

        double temperatura = sensorTemperatura.getValor();

        if (temperatura >= 28) {
            ventilador.setVelocidad(3);
        } else if (temperatura >= 25) {
            ventilador.setVelocidad(2);
        } else if (temperatura >= 22) {
            ventilador.setVelocidad(1);
        } else {
            ventilador.setVelocidad(0);
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
