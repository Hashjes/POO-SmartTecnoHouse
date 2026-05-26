package modelo;

/**
 * Actuador concreto para controlar un ventilador.
 */
public class ActuadorVentilador extends Actuador {

    /**
     * Crea un ventilador con estado inicial OFF.
     */
    public ActuadorVentilador() {
        super("fan", "Ventilador", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        String accionNormalizada = accion.toUpperCase();

        if (!esAccionValida(accionNormalizada)) {
            throw new IllegalArgumentException("Acción inválida para ventilador: " + accion);
        }

        this.estadoActual = accionNormalizada;
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"OFF", "LOW", "MED", "HIGH"};
    }
}
