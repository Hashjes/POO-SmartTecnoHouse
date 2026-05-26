package modelo;

/**
 * Actuador concreto para controlar una bombilla.
 */
public class ActuadorBombilla extends Actuador {

    /**
     * Crea una bombilla con estado inicial OFF.
     */
    public ActuadorBombilla() {
        super("bulb", "Bombilla", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        String accionNormalizada = accion.toUpperCase();

        if (!esAccionValida(accionNormalizada)) {
            throw new IllegalArgumentException("Acción inválida para bombilla: " + accion);
        }

        this.estadoActual = accionNormalizada;
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"OFF", "ON"};
    }
}
