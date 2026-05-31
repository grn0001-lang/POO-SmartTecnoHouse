package modelo.reglas;

import modelo.actuadores.Actuador;
import modelo.actuadores.ActuadorBombilla;
import modelo.sensores.Sensor;
import modelo.sensores.SensorLuz;
import modelo.sensores.SensorPresencia;

import java.util.List;

/**
 * Regla que enciende la bombilla si hay presencia y poca luz ambiental.
 */
public class ReglaIluminacionAutomatica implements Regla {

    private static final double UMBRAL_LUZ_BAJA = 300.0;

    @Override
    public String getNombre() {
        return "Iluminación automática";
    }

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        SensorLuz sensorLuz = buscarSensor(sensores, SensorLuz.class);
        SensorPresencia sensorPresencia = buscarSensor(sensores, SensorPresencia.class);
        ActuadorBombilla bombilla = buscarActuador(actuadores, ActuadorBombilla.class);

        if (sensorLuz == null || sensorPresencia == null || bombilla == null) {
            return;
        }

        boolean hayPresencia = sensorPresencia.isPresenciaDetectada();
        boolean pocaLuz = sensorLuz.getValor() < UMBRAL_LUZ_BAJA;

        if (hayPresencia && pocaLuz) {
            bombilla.encender();
        } else {
            bombilla.apagar();
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