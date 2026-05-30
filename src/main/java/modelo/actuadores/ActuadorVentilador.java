package modelo.actuadores;

/**
 * Actuador que representa un ventilador inteligente.
 */
public class ActuadorVentilador extends Actuador {

    private int velocidad;

    public ActuadorVentilador(String id, String nombre, String ubicacion) {
        super(id, nombre, ubicacion);
        this.velocidad = 0;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        if (velocidad < 0) {
            this.velocidad = 0;
        } else if (velocidad > 3) {
            this.velocidad = 3;
        } else {
            this.velocidad = velocidad;
        }

        if (this.velocidad > 0) {
            encender();
        } else {
            apagar();
        }
    }

    @Override
    public String getEstado() {
        if (!isActivo()) {
            return getNombre() + ": inactivo";
        }

        if (!isEncendido()) {
            return getNombre() + ": apagado";
        }

        return getNombre() + ": encendido, velocidad " + velocidad;
    }
}