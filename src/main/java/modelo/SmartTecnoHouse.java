package modelo;

import modelo.actuadores.Actuador;
import modelo.actuadores.ActuadorBombilla;
import modelo.actuadores.ActuadorPersiana;
import modelo.actuadores.ActuadorVentilador;
import modelo.reglas.Regla;
import modelo.reglas.ReglaControlHumedad;
import modelo.reglas.ReglaIluminacionAutomatica;
import modelo.reglas.ReglaVentilacionConfortable;
import modelo.sensores.Sensor;
import modelo.sensores.SensorHumedad;
import modelo.sensores.SensorLuz;
import modelo.sensores.SensorPresencia;
import modelo.sensores.SensorTemperatura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase principal del modelo de dominio de Smart TecnoHouse.
 * Gestiona sensores, actuadores y reglas automáticas del sistema domótico.
 */
public class SmartTecnoHouse {

    private final List<Sensor> sensores;
    private final List<Actuador> actuadores;
    private final List<Regla> reglas;

    public SmartTecnoHouse() {
        this.sensores = new ArrayList<>();
        this.actuadores = new ArrayList<>();
        this.reglas = new ArrayList<>();
        inicializarSistema();
    }

    /**
     * Inicializa el sistema con los dispositivos mínimos y las reglas automáticas.
     */
    private void inicializarSistema() {
        sensores.add(new SensorTemperatura("S001", "Sensor de temperatura", "Salón"));
        sensores.add(new SensorLuz("S002", "Sensor de luz", "Salón"));
        sensores.add(new SensorPresencia("S003", "Sensor de presencia", "Entrada"));
        sensores.add(new SensorHumedad("S004", "Sensor de humedad", "Dormitorio"));

        actuadores.add(new ActuadorBombilla("A001", "Bombilla inteligente", "Salón"));
        actuadores.add(new ActuadorVentilador("A002", "Ventilador inteligente", "Salón"));
        actuadores.add(new ActuadorPersiana("A003", "Persiana inteligente", "Dormitorio"));

        reglas.add(new ReglaIluminacionAutomatica());
        reglas.add(new ReglaVentilacionConfortable());
        reglas.add(new ReglaControlHumedad());
    }

    public List<Sensor> getSensores() {
        return Collections.unmodifiableList(sensores);
    }

    public List<Actuador> getActuadores() {
        return Collections.unmodifiableList(actuadores);
    }

    public List<Regla> getReglas() {
        return Collections.unmodifiableList(reglas);
    }

    /**
     * Simula una nueva lectura de todos los sensores activos.
     */
    public void actualizarSensores() {
        for (Sensor sensor : sensores) {
            if (sensor.isActivo()) {
                sensor.leerValor();
            }
        }
    }

    /**
     * Aplica todas las reglas automáticas registradas en el sistema.
     */
    public void aplicarReglasAutomaticas() {
        for (Regla regla : reglas) {
            regla.aplicar(sensores, actuadores);
        }
    }

    /**
     * Actualiza sensores y después aplica las reglas automáticas.
     */
    public void ejecutarCicloAutomatico() {
        actualizarSensores();
        aplicarReglasAutomaticas();
    }

    /**
     * Devuelve un resumen textual del estado de los sensores.
     */
    public String obtenerEstadoSensores() {
        StringBuilder resultado = new StringBuilder();

        for (Sensor sensor : sensores) {
            resultado.append(sensor.getEstado()).append(System.lineSeparator());
        }

        return resultado.toString();
    }

    /**
     * Devuelve un resumen textual del estado de los actuadores.
     */
    public String obtenerEstadoActuadores() {
        StringBuilder resultado = new StringBuilder();

        for (Actuador actuador : actuadores) {
            resultado.append(actuador.getEstado()).append(System.lineSeparator());
        }

        return resultado.toString();
    }

    /**
     * Devuelve un resumen textual de las reglas disponibles.
     */
    public String obtenerReglasDisponibles() {
        StringBuilder resultado = new StringBuilder();

        for (Regla regla : reglas) {
            resultado.append("- ").append(regla.getNombre()).append(System.lineSeparator());
        }

        return resultado.toString();
    }

    /**
     * Devuelve el estado completo del sistema.
     */
    public String obtenerResumenSistema() {
        return "=== SENSORES ===" + System.lineSeparator()
                + obtenerEstadoSensores()
                + System.lineSeparator()
                + "=== ACTUADORES ===" + System.lineSeparator()
                + obtenerEstadoActuadores()
                + System.lineSeparator()
                + "=== REGLAS ===" + System.lineSeparator()
                + obtenerReglasDisponibles();
    }
}
