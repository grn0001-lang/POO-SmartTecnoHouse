package modelo.persistencia;

import modelo.SmartTecnoHouse;
import modelo.actuadores.Actuador;
import modelo.actuadores.ActuadorPersiana;
import modelo.actuadores.ActuadorVentilador;
import modelo.reglas.Regla;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase responsable de guardar y cargar el estado del sistema domótico.
 * Usa un fichero JSON sencillo para evitar dependencias externas.
 */
public class GestorPersistencia {

    private static final Path RUTA_ESTADO = Path.of("estado.json");

    private GestorPersistencia() {
        // Constructor privado para evitar instanciación.
    }

    public static void guardarEstado(SmartTecnoHouse sistema) throws IOException {
        StringBuilder json = new StringBuilder();

        json.append("{").append(System.lineSeparator());
        json.append("  \"actuadores\": [").append(System.lineSeparator());

        for (int i = 0; i < sistema.getActuadores().size(); i++) {
            Actuador actuador = sistema.getActuadores().get(i);

            json.append("    {").append(System.lineSeparator());
            json.append("      \"id\": \"").append(escapar(actuador.getId())).append("\",").append(System.lineSeparator());
            json.append("      \"nombre\": \"").append(escapar(actuador.getNombre())).append("\",").append(System.lineSeparator());
            json.append("      \"tipo\": \"").append(actuador.getClass().getSimpleName()).append("\",").append(System.lineSeparator());
            json.append("      \"encendido\": ").append(actuador.isEncendido());

            if (actuador instanceof ActuadorVentilador ventilador) {
                json.append(",").append(System.lineSeparator());
                json.append("      \"velocidad\": ").append(ventilador.getVelocidad());
            } else if (actuador instanceof ActuadorPersiana persiana) {
                json.append(",").append(System.lineSeparator());
                json.append("      \"porcentajeApertura\": ").append(persiana.getPorcentajeApertura());
            }

            json.append(System.lineSeparator());
            json.append("    }");

            if (i < sistema.getActuadores().size() - 1) {
                json.append(",");
            }

            json.append(System.lineSeparator());
        }

        json.append("  ],").append(System.lineSeparator());
        json.append("  \"reglas\": [").append(System.lineSeparator());

        for (int i = 0; i < sistema.getReglas().size(); i++) {
            Regla regla = sistema.getReglas().get(i);

            json.append("    {").append(System.lineSeparator());
            json.append("      \"nombre\": \"").append(escapar(regla.getNombre())).append("\",").append(System.lineSeparator());
            json.append("      \"activa\": true").append(System.lineSeparator());
            json.append("    }");

            if (i < sistema.getReglas().size() - 1) {
                json.append(",");
            }

            json.append(System.lineSeparator());
        }

        json.append("  ]").append(System.lineSeparator());
        json.append("}").append(System.lineSeparator());

        Files.writeString(RUTA_ESTADO, json.toString());
    }

    public static boolean cargarEstado(SmartTecnoHouse sistema) throws IOException {
        if (!Files.exists(RUTA_ESTADO)) {
            return false;
        }

        String contenido = Files.readString(RUTA_ESTADO);

        for (Actuador actuador : sistema.getActuadores()) {
            String bloque = extraerBloqueActuador(contenido, actuador.getId());

            if (bloque == null) {
                continue;
            }

            boolean encendido = extraerBoolean(bloque, "encendido", false);

            if (actuador instanceof ActuadorVentilador ventilador) {
                int velocidad = extraerEntero(bloque, "velocidad", 0);
                ventilador.setVelocidad(velocidad);
            } else if (actuador instanceof ActuadorPersiana persiana) {
                int porcentaje = extraerEntero(bloque, "porcentajeApertura", 0);
                persiana.setPorcentajeApertura(porcentaje);
            } else {
                if (encendido) {
                    actuador.encender();
                } else {
                    actuador.apagar();
                }
            }
        }

        return true;
    }

    private static String extraerBloqueActuador(String contenido, String id) {
        String patronId = "\"id\": \"" + id + "\"";
        int posicionId = contenido.indexOf(patronId);

        if (posicionId == -1) {
            return null;
        }

        int inicio = contenido.lastIndexOf("{", posicionId);
        int fin = contenido.indexOf("}", posicionId);

        if (inicio == -1 || fin == -1) {
            return null;
        }

        return contenido.substring(inicio, fin + 1);
    }

    private static boolean extraerBoolean(String texto, String clave, boolean valorPorDefecto) {
        Pattern patron = Pattern.compile("\"" + clave + "\"\\s*:\\s*(true|false)");
        Matcher matcher = patron.matcher(texto);

        if (matcher.find()) {
            return Boolean.parseBoolean(matcher.group(1));
        }

        return valorPorDefecto;
    }

    private static int extraerEntero(String texto, String clave, int valorPorDefecto) {
        Pattern patron = Pattern.compile("\"" + clave + "\"\\s*:\\s*(\\d+)");
        Matcher matcher = patron.matcher(texto);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return valorPorDefecto;
    }

    private static String escapar(String texto) {
        return texto.replace("\"", "\\\"");
    }
}