package modelo;

/**
 * Clase abstracta base para todos los sensores del sistema.
 * Contiene la lógica común de identificación, unidad, rango y último valor leído.
 */
public abstract class Sensor implements IDispositivo {

    protected String id;
    protected String nombre;
    protected String unidad;
    protected double valor;
    protected double minimo;
    protected double maximo;

    /**
     * Crea un sensor con sus datos principales.
     *
     * @param id identificador único del sensor.
     * @param nombre nombre legible del sensor.
     * @param unidad unidad de medida.
     * @param minimo valor mínimo permitido.
     * @param maximo valor máximo permitido.
     */
    public Sensor(String id, String nombre, String unidad, double minimo, double maximo) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.minimo = minimo;
        this.maximo = maximo;
        this.valor = minimo;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el último valor leído por el sensor.
     *
     * @return valor actual del sensor.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Actualiza el valor del sensor validando el rango permitido.
     *
     * @param nuevoValor nuevo valor del sensor.
     * @throws IllegalArgumentException si el valor está fuera de rango.
     */
    public void setValor(double nuevoValor) {
        if (nuevoValor < minimo || nuevoValor > maximo) {
            throw new IllegalArgumentException(
                    "Valor fuera de rango para " + id + ": " + nuevoValor
                            + ". Rango permitido [" + minimo + ", " + maximo + "]"
            );
        }
        this.valor = nuevoValor;
    }

    /**
     * Método polimórfico para actualizar el valor del sensor.
     * Cada sensor concreto decide cómo simular o actualizar su valor.
     */
    public abstract void actualizarValor();

    @Override
    public String getEstadoActual() {
        return valor + " " + unidad;
    }
}