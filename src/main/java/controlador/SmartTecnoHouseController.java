package controlador;

import modelo.SmartTecnoHouse;
import modelo.persistencia.GestorLog;
import modelo.persistencia.GestorPersistencia;
import vista.VentanaPrincipal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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
        cargarEstadoInicial();
        actualizarVistaCompleta();
        guardarEstadoAlCerrar();
    }

    private void inicializarEventos() {
        vista.getBotonActualizarSensores().addActionListener(e -> actualizarSensores());
        vista.getBotonAplicarReglas().addActionListener(e -> aplicarReglas());
        vista.getBotonEjecutarCiclo().addActionListener(e -> ejecutarCicloAutomatico());
        vista.getBotonGuardarEstado().addActionListener(e -> guardarEstado());
        vista.getBotonCargarEstado().addActionListener(e -> cargarEstado());
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
        registrarEstadoActuadores("Aplicación de reglas automáticas");
        vista.mostrarMensaje("Reglas automáticas aplicadas correctamente.");
    }

    private void ejecutarCicloAutomatico() {
        modelo.ejecutarCicloAutomatico();
        actualizarVistaCompleta();
        registrarEstadoActuadores("Ejecución de ciclo automático");
        vista.mostrarMensaje("Ciclo automático ejecutado: sensores actualizados y reglas aplicadas.");
    }

    private void guardarEstado() {
        try {
            GestorPersistencia.guardarEstado(modelo);
            GestorLog.registrarEstadoActuadores(modelo.getActuadores(), "Guardado de estado");
            vista.mostrarMensaje("Estado guardado correctamente en estado.json.");
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar el estado: " + e.getMessage());
        }
    }

    private void cargarEstado() {
        try {
            boolean cargado = GestorPersistencia.cargarEstado(modelo);
            actualizarVistaCompleta();

            if (cargado) {
                vista.mostrarMensaje("Estado cargado correctamente desde estado.json.");
            } else {
                vista.mostrarMensaje("No existe estado.json. Se mantiene el estado inicial.");
            }
        } catch (IOException e) {
            vista.mostrarMensaje("Error al cargar el estado: " + e.getMessage());
        }
    }

    private void cargarEstadoInicial() {
        try {
            boolean cargado = GestorPersistencia.cargarEstado(modelo);

            if (cargado) {
                vista.mostrarMensaje("Estado inicial cargado desde estado.json.");
            }
        } catch (IOException e) {
            vista.mostrarMensaje("No se pudo cargar el estado inicial: " + e.getMessage());
        }
    }

    private void guardarEstadoAlCerrar() {
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    GestorPersistencia.guardarEstado(modelo);
                    GestorLog.registrarEstadoActuadores(modelo.getActuadores(), "Cierre de aplicación");
                } catch (IOException ex) {
                    System.err.println("No se pudo guardar el estado al cerrar: " + ex.getMessage());
                }
            }
        });
    }

    private void registrarEstadoActuadores(String accion) {
        try {
            GestorLog.registrarEstadoActuadores(modelo.getActuadores(), accion);
        } catch (IOException e) {
            vista.mostrarMensaje("No se pudo escribir en actuators.log: " + e.getMessage());
        }
    }

    private void actualizarVistaCompleta() {
        vista.mostrarSensores(modelo.obtenerEstadoSensores());
        vista.mostrarActuadores(modelo.obtenerEstadoActuadores());
        vista.mostrarReglas(modelo.obtenerReglasDisponibles());
    }
}