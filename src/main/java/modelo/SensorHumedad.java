package modelo;

/**
 * Sensor adicional para medir la humedad relativa.
 */
public class SensorHumedad extends Sensor {

    public SensorHumedad() {
        super("humidity", "Sensor de Humedad", "%", 0, 100);
    }

    @Override
    public void actualizarValor() {
        double valorAleatorio = Math.random() * 100;
        setValor(Math.round(valorAleatorio * 10.0) / 10.0);
    }
}
