package modelo.dispositivos;

/**
 * Clase base abstracta para representar cualquier dispositivo domótico.
 * Contiene los atributos comunes de modelo.sensores y modelo.actuadores.
 */
public abstract class Dispositivo implements IDispositivo {

    private final String id;
    private String nombre;
    private String ubicacion;
    private boolean activo;

    public Dispositivo(String id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.activo = true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public boolean isActivo() {
        return activo;
    }

    @Override
    public void activar() {
        this.activo = true;
    }

    @Override
    public void desactivar() {
        this.activo = false;
    }

    @Override
    public String toString() {
        return nombre + " (" + ubicacion + ") - " + getEstado();
    }
}