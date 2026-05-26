package modelo;

/**
 * Sensor concreto para medir el nivel de luminosidad.
 */
public class SensorLuz extends Sensor {

    /**
     * Crea un sensor de luz con ID light, unidad lux y rango [0, 1000].
     */
    public SensorLuz() {
        super("light", "Sensor de Luz", "lux", 0, 1000);
    }

    @Override
    public void actualizarValor() {
        double valorAleatorio = Math.random() * 1000;
        setValor(Math.round(valorAleatorio * 10.0) / 10.0);
    }
}
