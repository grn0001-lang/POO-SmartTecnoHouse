package modelo.actuadores;

import modelo.dispositivos.Dispositivo;

/**
 * Clase abstracta que representa un actuador genérico del sistema domótico.
 * Los actuadores son dispositivos capaces de modificar el estado de la vivienda.
 */
public abstract class Actuador extends Dispositivo {

    private boolean encendido;

    public Actuador(String id, String nombre, String ubicacion) {
        super(id, nombre, ubicacion);
        this.encendido = false;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void encender() {
        if (isActivo()) {
            this.encendido = true;
        }
    }

    public void apagar() {
        this.encendido = false;
    }

    @Override
    public String getEstado() {
        if (!isActivo()) {
            return getNombre() + ": inactivo";
        }

        return getNombre() + ": " + (encendido ? "encendido" : "apagado");
    }
}
