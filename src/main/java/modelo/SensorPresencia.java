package modelo;

/**
 * Sensor concreto para detectar presencia.
 */
public class SensorPresencia extends Sensor {

    public SensorPresencia() {
        super("pir", "Sensor de Presencia", "boolean", 0, 1);
    }

    @Override
    public void actualizarValor() {
        double valorAleatorio = Math.random() < 0.5 ? 0 : 1;
        setValor(valorAleatorio);
    }

    @Override
    public String getEstadoActual() {
        return getValor() == 1 ? "Presencia detectada" : "Sin presencia";
    }
}
