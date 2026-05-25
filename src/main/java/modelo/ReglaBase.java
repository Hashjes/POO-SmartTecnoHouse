package modelo;

/**
 * Clase base para reglas de negocio.
 * Centraliza el ID, el nombre y el estado activo/inactivo.
 */
public abstract class ReglaBase implements Regla {

    protected String id;
    protected String nombre;
    protected boolean activa;

    /**
     * Crea una regla base.
     *
     * @param id identificador de la regla.
     * @param nombre nombre legible de la regla.
     * @param activa estado inicial de la regla.
     */
    public ReglaBase(String id, String nombre, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.activa = activa;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean isActiva() {
        return activa;
    }

    @Override
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}