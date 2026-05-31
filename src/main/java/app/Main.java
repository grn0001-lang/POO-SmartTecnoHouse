package app;

import controlador.SmartTecnoHouseController;
import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;

import javax.swing.*;

/**
 * Punto de entrada de la aplicación Smart TecnoHouse.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmartTecnoHouse modelo = new SmartTecnoHouse();
            VentanaPrincipal vista = new VentanaPrincipal();

            new SmartTecnoHouseController(modelo, vista);

            vista.setVisible(true);
        });
    }
}