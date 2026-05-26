package modelo;

import java.util.Arrays;

/**
 * Clase abstracta base para todos los actuadores del sistema.
 * Contiene la lógica común de identificación y estado actual.
 */
public abstract class Actuador implements IDispositivo {

    /** Identificador único del actuador. */
    protected String id;

    /** Nombre legible del actuador. */
    protected String nombre;

    /** Estado actual del actuador. */
    protected String estadoActual;

    /**
     * Crea un actuador con sus datos principales.
     *
     * @param id identificador único del actuador.
     * @param nombre nombre legible del actuador.
     * @param estadoInicial estado inicial del actuador.
     */
    public Actuador(String id, String nombre, String estadoInicial) {
        this.id = id;
        this.nombre = nombre;
        this.estadoActual = estadoInicial;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getEstadoActual() {
        return estadoActual;
    }

    /**
     * Ejecuta una acción sobre el actuador.
     * Cada actuador concreto valida y aplica sus acciones permitidas.
     *
     * @param accion acción a ejecutar.
     */
    public abstract void ejecutarAccion(String accion);

    /**
     * Devuelve las acciones permitidas por el actuador.
     *
     * @return array de acciones válidas.
     */
    public abstract String[] getAccionesPosibles();

    /**
     * Comprueba si una acción está permitida para el actuador.
     *
     * @param accion acción a validar.
     * @return true si la acción es válida, false en caso contrario.
     */
    protected boolean esAccionValida(String accion) {
        return Arrays.asList(getAccionesPosibles()).contains(accion);
    }
}