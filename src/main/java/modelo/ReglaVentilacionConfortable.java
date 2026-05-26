package modelo;

/**
 * R1. Ventilación confortable:
 * si la temperatura es alta (>28) y hay presencia, el ventilador pasa a HIGH.
 */
public class ReglaVentilacionConfortable extends ReglaBase {

    /**
     * Crea la regla R1 activa por defecto.
     */
    public ReglaVentilacionConfortable() {
        super("R1", "Ventilación confortable", true);
    }

    @Override
    public void aplicar(SmartTecnoHouse sistema) {
        double temp = sistema.getValorSensor("temp");
        double pir = sistema.getValorSensor("pir");

        if (temp > 28 && pir == 1) {
            sistema.ejecutarAccion("fan", "HIGH", "RULE");
        }
    }
}