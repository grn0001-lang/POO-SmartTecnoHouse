package modelo.dispositivos;

/**
 * Interfaz común para todos los dispositivos del sistema domótico.
 * Permite tratar modelo.sensores y modelo.actuadores de forma polimórfica.
 */
public interface IDispositivo {

    String getId();

    String getNombre();

    String getUbicacion();

    boolean isActivo();

    void activar();

    void desactivar();

    String getEstado();
}