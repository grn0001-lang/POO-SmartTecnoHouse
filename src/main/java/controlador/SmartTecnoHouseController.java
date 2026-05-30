package controlador;

import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;

/**
 * Controlador principal de la aplicación.
 * Conecta la vista Swing con el modelo de dominio SmartTecnoHouse.
 */
public class SmartTecnoHouseController {

    private final SmartTecnoHouse modelo;
    private final VentanaPrincipal vista;

    public SmartTecnoHouseController(SmartTecnoHouse modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;
        inicializarEventos();
        actualizarVistaCompleta();
    }

    private void inicializarEventos() {
        vista.getBotonActualizarSensores().addActionListener(e -> actualizarSensores());
        vista.getBotonAplicarReglas().addActionListener(e -> aplicarReglas());
        vista.getBotonEjecutarCiclo().addActionListener(e -> ejecutarCicloAutomatico());
        vista.getBotonLimpiarMensajes().addActionListener(e -> vista.limpiarMensajes());
    }

    private void actualizarSensores() {
        modelo.actualizarSensores();
        actualizarVistaCompleta();
        vista.mostrarMensaje("Sensores actualizados correctamente.");
    }

    private void aplicarReglas() {
        modelo.aplicarReglasAutomaticas();
        actualizarVistaCompleta();
        vista.mostrarMensaje("Reglas automáticas aplicadas correctamente.");
    }

    private void ejecutarCicloAutomatico() {
        modelo.ejecutarCicloAutomatico();
        actualizarVistaCompleta();
        vista.mostrarMensaje("Ciclo automático ejecutado: sensores actualizados y reglas aplicadas.");
    }

    private void actualizarVistaCompleta() {
        vista.mostrarSensores(modelo.obtenerEstadoSensores());
        vista.mostrarActuadores(modelo.obtenerEstadoActuadores());
        vista.mostrarReglas(modelo.obtenerReglasDisponibles());
    }
}