package modelo.sensores;

import modelo.dispositivos.Dispositivo;

/**
 * Clase abstracta que representa un sensor genérico del sistema domótico.
 * Los sensores tienen un valor medido y una unidad de medida.
 */
public abstract class Sensor extends Dispositivo {

    private double valor;
    private String unidad;

    public Sensor(String id, String nombre, String ubicacion, String unidad) {
        super(id, nombre, ubicacion);
        this.unidad = unidad;
        this.valor = 0.0;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * Simula la lectura del sensor.
     * Cada sensor concreto implementará su propia forma de actualizar el valor.
     */
    public abstract void leerValor();

    @Override
    public String getEstado() {
        if (!isActivo()) {
            return "Sensor inactivo";
        }

        return getNombre() + ": " + valor + " " + unidad;
    }
}