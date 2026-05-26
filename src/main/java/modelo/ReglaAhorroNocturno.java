package modelo;

/**
 * R3. Ahorro nocturno:
 * si hay mucha luz y temperatura confortable, o si no hay presencia,
 * apaga ventilador y bombilla.
 */
public class ReglaAhorroNocturno extends ReglaBase {

    /**
     * Crea la regla R3 desactivada por defecto.
     */
    public ReglaAhorroNocturno() {
        super("R3", "Ahorro nocturno", false);
    }

    @Override
    public void aplicar(SmartTecnoHouse sistema) {
        double temp = sistema.getValorSensor("temp");
        double light = sistema.getValorSensor("light");
        double pir = sistema.getValorSensor("pir");

        boolean condicionA = light > 700 && temp >= 20 && temp <= 25;
        boolean condicionB = pir == 0;

        if (condicionA || condicionB) {
            sistema.ejecutarAccion("fan", "OFF", "RULE");
            sistema.ejecutarAccion("bulb", "OFF", "RULE");
        }
    }
}