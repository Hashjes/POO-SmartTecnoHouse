package modelo;

/**
 * R2. Iluminación automática:
 * si la luminosidad es baja (<300 lux) y hay presencia, la bombilla se enciende.
 */
public class ReglaIluminacionAutomatica extends ReglaBase {

    public ReglaIluminacionAutomatica() {
        super("R2", "Iluminación automática", true);
    }

    @Override
    public void aplicar(SmartTecnoHouse sistema) {
        double light = sistema.getValorSensor("light");
        double pir = sistema.getValorSensor("pir");

        if (light < 300 && pir == 1) {
            sistema.ejecutarAccion("bulb", "ON", "RULE");
        }
    }
}