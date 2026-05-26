package controlador;

import modelo.Actuador;
import modelo.PersistenciaService;
import modelo.Regla;
import modelo.SmartTecnoHouse;
import vista.MainFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Controlador principal de la aplicación.
 * Conecta la Vista Swing con el Modelo SmartTecnoHouse.
 */
public class SmartTecnoHouseController {

    private final SmartTecnoHouse modelo;
    private final MainFrame vista;
    private final PersistenciaService persistenciaService;

    /**
     * Crea el controlador principal.
     *
     * @param modelo modelo de dominio.
     * @param vista vista Swing.
     * @param persistenciaService servicio de persistencia.
     */
    public SmartTecnoHouseController(
            SmartTecnoHouse modelo,
            MainFrame vista,
            PersistenciaService persistenciaService
    ) {
        this.modelo = modelo;
        this.vista = vista;
        this.persistenciaService = persistenciaService;
        this.vista.setControlador(this);
    }

    /**
     * Inicializa la aplicación cargando estado persistido y mostrando la vista.
     */
    public void iniciar() {
        try {
            persistenciaService.cargarEstado(modelo);
        } catch (IOException e) {
            vista.mostrarError("No se pudo cargar el estado inicial: " + e.getMessage());
        }

        vista.refrescar(modelo);
        vista.setVisible(true);
    }

    /**
     * Actualiza todos los sensores y refresca la vista.
     */
    public void actualizarSensores() {
        modelo.actualizarSensores();
        vista.refrescar(modelo);
    }

    /**
     * Aplica las reglas activas y refresca la vista.
     */
    public void aplicarReglas() {
        try {
            modelo.aplicarReglas();
            vista.refrescar(modelo);
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    /**
     * Ejecuta una acción manual seleccionada por el usuario.
     */
    public void ejecutarAccionManual() {
        String actuadorId = vista.getActuadorSeleccionado();
        String accion = vista.getAccionSeleccionada();

        if (actuadorId == null || accion == null) {
            vista.mostrarError("Debe seleccionar un actuador y una acción.");
            return;
        }

        try {
            modelo.ejecutarAccionManual(actuadorId, accion);
            vista.refrescar(modelo);
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    /**
     * Cambia el estado de una regla.
     *
     * @param reglaId ID de la regla.
     * @param activa nuevo estado.
     */
    public void cambiarEstadoRegla(String reglaId, boolean activa) {
        Regla regla = modelo.buscarRegla(reglaId);

        if (regla == null) {
            vista.mostrarError("Regla no encontrada: " + reglaId);
            return;
        }

        regla.setActiva(activa);
    }

    /**
     * Actualiza el combo de acciones según el actuador seleccionado.
     */
    public void actualizarAccionesActuador() {
        String actuadorId = vista.getActuadorSeleccionado();

        if (actuadorId == null) {
            return;
        }

        Actuador actuador = modelo.buscarActuador(actuadorId);
        vista.actualizarComboAcciones(actuador);
    }

    /**
     * Guarda el estado persistente y el log de actuadores.
     */
    public void guardarEstado() {
        if (guardarEstadoSilencioso()) {
            vista.mostrarMensaje("Estado guardado correctamente.");
        }
    }

    /**
     * Guarda el estado al cerrar la aplicación sin mostrar mensaje de éxito.
     */
    public void guardarEstadoAlSalir() {
        guardarEstadoSilencioso();
    }

    /**
     * Carga el estado persistido y refresca la vista.
     */
    public void cargarEstado() {
        try {
            persistenciaService.cargarEstado(modelo);
            vista.refrescar(modelo);
            vista.mostrarMensaje("Estado cargado correctamente.");
        } catch (IOException e) {
            vista.mostrarError("No se pudo cargar el estado: " + e.getMessage());
        }
    }

    private void guardarActuatorLog() throws IOException {
        Files.write(Path.of("actuators.log"), modelo.getActuatorLog());
    }

    private boolean guardarEstadoSilencioso() {
        try {
            persistenciaService.guardarEstado(modelo);
            guardarActuatorLog();
            return true;
        } catch (IOException e) {
            vista.mostrarError("No se pudo guardar el estado: " + e.getMessage());
            return false;
        }
    }
}
