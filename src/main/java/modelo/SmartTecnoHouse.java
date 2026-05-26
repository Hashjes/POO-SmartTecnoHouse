package modelo;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo principal del sistema Smart TecnoHouse.
 * Centraliza sensores, actuadores, reglas y log de acciones.
 */
public class SmartTecnoHouse {

    private List<Sensor> sensores;
    private List<Actuador> actuadores;
    private List<Regla> reglas;
    private List<String> actuatorLog;

    private static final DateTimeFormatter LOG_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .withZone(ZoneOffset.UTC);

    /**
     * Crea el sistema domótico con sensores, actuadores y reglas iniciales.
     */
    public SmartTecnoHouse() {
        sensores = new ArrayList<>();
        actuadores = new ArrayList<>();
        reglas = new ArrayList<>();
        actuatorLog = new ArrayList<>();

        inicializarSensores();
        inicializarActuadores();
        inicializarReglas();
    }

    private void inicializarSensores() {
        sensores.add(new SensorTemperatura());
        sensores.add(new SensorLuz());
        sensores.add(new SensorPresencia());
        sensores.add(new SensorHumedad());
    }

    private void inicializarActuadores() {
        actuadores.add(new ActuadorBombilla());
        actuadores.add(new ActuadorVentilador());
        actuadores.add(new ActuadorEnchufe());
    }

    private void inicializarReglas() {
        reglas.add(new ReglaVentilacionConfortable());
        reglas.add(new ReglaIluminacionAutomatica());
        reglas.add(new ReglaAhorroNocturno());
    }

    /**
     * Devuelve la lista de sensores registrados en el sistema.
     *
     * @return lista de sensores.
     */
    public List<Sensor> getSensores() {
        return sensores;
    }

    /**
     * Devuelve la lista de actuadores registrados en el sistema.
     *
     * @return lista de actuadores.
     */
    public List<Actuador> getActuadores() {
        return actuadores;
    }

    /**
     * Devuelve la lista de reglas configuradas en el sistema.
     *
     * @return lista de reglas.
     */
    public List<Regla> getReglas() {
        return reglas;
    }

    /**
     * Devuelve el log de acciones ejecutadas sobre actuadores.
     *
     * @return lista de líneas del log.
     */
    public List<String> getActuatorLog() {
        return actuatorLog;
    }

    /**
     * Actualiza todos los sensores usando su comportamiento polimórfico.
     */
    public void actualizarSensores() {
        for (Sensor sensor : sensores) {
            sensor.actualizarValor();
        }
    }

    /**
     * Aplica todas las reglas activas.
     */
    public void aplicarReglas() {
        for (Regla regla : reglas) {
            if (regla.isActiva()) {
                regla.aplicar(this);
            }
        }
    }

    /**
     * Ejecuta una acción manual sobre un actuador.
     *
     * @param actuadorId ID del actuador.
     * @param accion acción a ejecutar.
     */
    public void ejecutarAccionManual(String actuadorId, String accion) {
        ejecutarAccion(actuadorId, accion, "MANUAL");
    }

    /**
     * Ejecuta una acción sobre un actuador y registra la acción.
     *
     * @param actuadorId ID del actuador.
     * @param accion acción a ejecutar.
     * @param source origen de la acción: RULE o MANUAL.
     */
    public void ejecutarAccion(String actuadorId, String accion, String source) {
        Actuador actuador = buscarActuador(actuadorId);

        if (actuador == null) {
            throw new IllegalArgumentException("Actuador no encontrado: " + actuadorId);
        }

        actuador.ejecutarAccion(accion);
        registrarAccion(actuadorId, accion.toUpperCase(), source);
    }

    /**
     * Devuelve el valor de un sensor por ID.
     *
     * @param sensorId ID del sensor.
     * @return valor actual.
     */
    public double getValorSensor(String sensorId) {
        Sensor sensor = buscarSensor(sensorId);

        if (sensor == null) {
            throw new IllegalArgumentException("Sensor no encontrado: " + sensorId);
        }

        return sensor.getValor();
    }

    /**
     * Busca un sensor por ID.
     *
     * @param sensorId ID del sensor.
     * @return sensor encontrado o null.
     */
    public Sensor buscarSensor(String sensorId) {
        for (Sensor sensor : sensores) {
            if (sensor.getID().equals(sensorId)) {
                return sensor;
            }
        }
        return null;
    }

    /**
     * Busca un actuador por ID.
     *
     * @param actuadorId ID del actuador.
     * @return actuador encontrado o null.
     */
    public Actuador buscarActuador(String actuadorId) {
        for (Actuador actuador : actuadores) {
            if (actuador.getID().equals(actuadorId)) {
                return actuador;
            }
        }
        return null;
    }

    /**
     * Busca una regla por ID.
     *
     * @param reglaId ID de la regla.
     * @return regla encontrada o null.
     */
    public Regla buscarRegla(String reglaId) {
        for (Regla regla : reglas) {
            if (regla.getId().equalsIgnoreCase(reglaId)) {
                return regla;
            }
        }
        return null;
    }

    private void registrarAccion(String actuadorId, String accion, String source) {
        String timestamp = LOG_FORMATTER.format(Instant.now());
        String linea = String.format("%-25s%-12s%-8s%-10s", timestamp, actuadorId, accion, source);
        actuatorLog.add(linea);
    }
}