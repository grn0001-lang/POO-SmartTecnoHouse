package modelo.actuadores;

/**
 * Actuador adicional que representa una persiana inteligente.
 */
public class ActuadorPersiana extends Actuador {

    private int porcentajeApertura;

    public ActuadorPersiana(String id, String nombre, String ubicacion) {
        super(id, nombre, ubicacion);
        this.porcentajeApertura = 0;
    }

    public int getPorcentajeApertura() {
        return porcentajeApertura;
    }

    public void subir() {
        this.porcentajeApertura = 100;
        encender();
    }

    public void bajar() {
        this.porcentajeApertura = 0;
        apagar();
    }

    public void setPorcentajeApertura(int porcentajeApertura) {
        if (porcentajeApertura < 0) {
            this.porcentajeApertura = 0;
        } else if (porcentajeApertura > 100) {
            this.porcentajeApertura = 100;
        } else {
            this.porcentajeApertura = porcentajeApertura;
        }

        if (this.porcentajeApertura > 0) {
            encender();
        } else {
            apagar();
        }
    }

    @Override
    public String getEstado() {
        if (!isActivo()) {
            return getNombre() + ": inactiva";
        }

        return getNombre() + ": apertura " + porcentajeApertura + "%";
    }
}