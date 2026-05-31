package modelo.persistencia;

import modelo.actuadores.Actuador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clase responsable de registrar en un fichero las acciones realizadas
 * sobre los actuadores del sistema domótico.
 */
public class GestorLog {

    private static final Path RUTA_LOG = Path.of("actuators.log");
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private GestorLog() {
        // Constructor privado para evitar instanciación.
    }

    public static void registrar(String mensaje) throws IOException {
        String fecha = LocalDateTime.now().format(FORMATO_FECHA);
        String linea = fecha + " | " + mensaje + System.lineSeparator();

        Files.writeString(
                RUTA_LOG,
                linea,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    public static void registrarEstadoActuadores(List<Actuador> actuadores, String accion) throws IOException {
        for (Actuador actuador : actuadores) {
            registrar(accion + " | " + actuador.getId() + " | "
                    + actuador.getNombre() + " | " + actuador.getEstado());
        }
    }
}