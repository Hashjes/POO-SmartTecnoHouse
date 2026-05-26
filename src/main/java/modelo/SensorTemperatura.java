package modelo;

/**
 * Sensor concreto para medir la temperatura ambiente.
 */
public class SensorTemperatura extends Sensor {

    /**
     * Crea un sensor de temperatura con ID temp, unidad °C y rango [-20, 50].
     */
    public SensorTemperatura() {
        super("temp", "Sensor de Temperatura", "°C", -20, 50);
    }

    @Override
    public void actualizarValor() {
        double valorAleatorio = -20 + (Math.random() * 70);
        setValor(Math.round(valorAleatorio * 10.0) / 10.0);
    }
}
