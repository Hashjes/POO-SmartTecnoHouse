package modelo;

/**
 * Sensor concreto para medir el nivel de luminosidad.
 */
public class SensorLuz extends Sensor {

    public SensorLuz() {
        super("light", "Sensor de Luz", "lux", 0, 1000);
    }

    @Override
    public void actualizarValor() {
        double valorAleatorio = Math.random() * 1000;
        setValor(Math.round(valorAleatorio * 10.0) / 10.0);
    }
}
