package modelo;

/**
 * Actuador adicional para controlar un enchufe inteligente.
 */
public class ActuadorEnchufe extends Actuador {

    public ActuadorEnchufe() {
        super("plug", "Enchufe Inteligente", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        String accionNormalizada = accion.toUpperCase();

        if (!esAccionValida(accionNormalizada)) {
            throw new IllegalArgumentException("Acción inválida para enchufe: " + accion);
        }

        this.estadoActual = accionNormalizada;
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"OFF", "ON"};
    }
}
