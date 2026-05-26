package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Servicio encargado de guardar y cargar el estado persistente del sistema.
 * Persiste estados de actuadores y configuración de reglas en formato JSON.
 */
public class PersistenciaService {

    private final Gson gson;
    private final Path rutaArchivo;

    /**
     * Crea un servicio de persistencia asociado a una ruta de fichero.
     *
     * @param rutaArchivo ruta del fichero JSON de estado.
     */
    public PersistenciaService(String rutaArchivo) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.rutaArchivo = Path.of(rutaArchivo);
    }

    /**
     * Guarda el estado actual de actuadores y reglas en JSON.
     *
     * @param sistema modelo principal SmartTecnoHouse.
     * @throws IOException si no se puede escribir el fichero.
     */
    public void guardarEstado(SmartTecnoHouse sistema) throws IOException {
        EstadoPersistido estado = new EstadoPersistido();

        for (Actuador actuador : sistema.getActuadores()) {
            estado.actuadores.put(actuador.getID(), actuador.getEstadoActual());
        }

        for (Regla regla : sistema.getReglas()) {
            estado.reglas.put(regla.getId(), regla.isActiva());
        }

        Path carpeta = rutaArchivo.getParent();
        if (carpeta != null) {
            Files.createDirectories(carpeta);
        }

        String json = gson.toJson(estado);
        Files.writeString(rutaArchivo, json);
    }

    /**
     * Carga el estado desde JSON y lo aplica al sistema.
     * Si el fichero no existe, no realiza ningún cambio.
     *
     * @param sistema modelo principal SmartTecnoHouse.
     * @throws IOException si existe el fichero pero no puede leerse.
     */
    public void cargarEstado(SmartTecnoHouse sistema) throws IOException {
        if (!Files.exists(rutaArchivo)) {
            return;
        }

        String json = Files.readString(rutaArchivo);
        EstadoPersistido estado = gson.fromJson(json, EstadoPersistido.class);

        if (estado == null) {
            return;
        }

        if (estado.actuadores != null) {
            for (Map.Entry<String, String> entry : estado.actuadores.entrySet()) {
                Actuador actuador = sistema.buscarActuador(entry.getKey());
                if (actuador != null) {
                    actuador.ejecutarAccion(entry.getValue());
                }
            }
        }

        if (estado.reglas != null) {
            for (Map.Entry<String, Boolean> entry : estado.reglas.entrySet()) {
                Regla regla = sistema.buscarRegla(entry.getKey());
                if (regla != null) {
                    regla.setActiva(entry.getValue());
                }
            }
        }
    }

    /**
     * Estructura interna que representa el contenido del JSON de persistencia.
     */
    private static class EstadoPersistido {
        Map<String, String> actuadores = new LinkedHashMap<>();
        Map<String, Boolean> reglas = new LinkedHashMap<>();
    }
}