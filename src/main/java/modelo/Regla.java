package modelo;

/**
 * Interface del patrón Strategy para representar reglas de negocio.
 * Cada regla decide cómo aplicar su lógica sobre el sistema SmartTecnoHouse.
 */
public interface Regla {

    /**
     * Devuelve el ID de la regla.
     *
     * @return ID de la regla.
     */
    String getId();

    /**
     * Devuelve el nombre legible de la regla.
     *
     * @return nombre de la regla.
     */
    String getNombre();

    /**
     * Indica si la regla está activa.
     *
     * @return true si está activa, false en caso contrario.
     */
    boolean isActiva();

    /**
     * Activa o desactiva la regla.
     *
     * @param activa nuevo estado de la regla.
     */
    void setActiva(boolean activa);

    /**
     * Aplica la regla sobre el sistema.
     *
     * @param sistema modelo principal del sistema domótico.
     */
    void aplicar(SmartTecnoHouse sistema);
}