package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Vista principal de la aplicación Smart TecnoHouse.
 * Contiene la interfaz gráfica construida con Swing.
 */
public class VentanaPrincipal extends JFrame {

    private final JTextArea areaSensores;
    private final JTextArea areaActuadores;
    private final JTextArea areaReglas;
    private final JTextArea areaMensajes;

    private final JButton botonActualizarSensores;
    private final JButton botonAplicarReglas;
    private final JButton botonEjecutarCiclo;
    private final JButton botonLimpiarMensajes;

    public VentanaPrincipal() {
        setTitle("Smart TecnoHouse - Sistema Domótico");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaSensores = crearAreaTexto();
        areaActuadores = crearAreaTexto();
        areaReglas = crearAreaTexto();
        areaMensajes = crearAreaTexto();

        botonActualizarSensores = new JButton("Actualizar sensores");
        botonAplicarReglas = new JButton("Aplicar reglas");
        botonEjecutarCiclo = new JButton("Ejecutar ciclo automático");
        botonLimpiarMensajes = new JButton("Limpiar mensajes");

        construirInterfaz();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCentral.add(crearPanelConTitulo("Sensores", areaSensores));
        panelCentral.add(crearPanelConTitulo("Actuadores", areaActuadores));
        panelCentral.add(crearPanelConTitulo("Reglas automáticas", areaReglas));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(botonActualizarSensores);
        panelBotones.add(botonAplicarReglas);
        panelBotones.add(botonEjecutarCiclo);
        panelBotones.add(botonLimpiarMensajes);

        JPanel panelInferior = crearPanelConTitulo("Mensajes del sistema", areaMensajes);
        panelInferior.setPreferredSize(new Dimension(900, 150));

        add(panelCentral, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.NORTH);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JTextArea crearAreaTexto() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        return area;
    }

    private JPanel crearPanelConTitulo(String titulo, JTextArea areaTexto) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        return panel;
    }

    public void mostrarSensores(String texto) {
        areaSensores.setText(texto);
    }

    public void mostrarActuadores(String texto) {
        areaActuadores.setText(texto);
    }

    public void mostrarReglas(String texto) {
        areaReglas.setText(texto);
    }

    public void mostrarMensaje(String mensaje) {
        areaMensajes.append(mensaje + System.lineSeparator());
    }

    public void limpiarMensajes() {
        areaMensajes.setText("");
    }

    public JButton getBotonActualizarSensores() {
        return botonActualizarSensores;
    }

    public JButton getBotonAplicarReglas() {
        return botonAplicarReglas;
    }

    public JButton getBotonEjecutarCiclo() {
        return botonEjecutarCiclo;
    }

    public JButton getBotonLimpiarMensajes() {
        return botonLimpiarMensajes;
    }
}