package vista;

import controlador.SmartTecnoHouseController;
import modelo.Actuador;
import modelo.Regla;
import modelo.Sensor;
import modelo.SmartTecnoHouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Vista principal de la aplicación Smart TecnoHouse.
 * Implementa la interfaz gráfica usando Swing.
 */
public class MainFrame extends JFrame {

    private JPanel sensoresPanel;
    private JPanel actuadoresPanel;
    private JPanel reglasPanel;
    private JTextArea logArea;

    private JComboBox<String> actuadorCombo;
    private JComboBox<String> accionCombo;

    private JButton actualizarSensoresButton;
    private JButton aplicarReglasButton;
    private JButton ejecutarManualButton;
    private JButton guardarEstadoButton;
    private JButton cargarEstadoButton;

    private SmartTecnoHouseController controlador;

    /**
     * Crea la ventana principal.
     */
    public MainFrame() {
        setTitle("Smart TecnoHouse - Sistema Domótico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        actualizarSensoresButton = new JButton("Actualizar sensores");
        aplicarReglasButton = new JButton("Aplicar reglas");
        guardarEstadoButton = new JButton("Guardar estado");
        cargarEstadoButton = new JButton("Cargar estado");

        panelSuperior.add(actualizarSensoresButton);
        panelSuperior.add(aplicarReglasButton);
        panelSuperior.add(guardarEstadoButton);
        panelSuperior.add(cargarEstadoButton);

        add(panelSuperior, BorderLayout.NORTH);

        sensoresPanel = new JPanel();
        sensoresPanel.setLayout(new BoxLayout(sensoresPanel, BoxLayout.Y_AXIS));

        actuadoresPanel = new JPanel();
        actuadoresPanel.setLayout(new BoxLayout(actuadoresPanel, BoxLayout.Y_AXIS));

        reglasPanel = new JPanel();
        reglasPanel.setLayout(new BoxLayout(reglasPanel, BoxLayout.Y_AXIS));

        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 10));
        panelCentral.add(crearPanelConTitulo("Sensores", sensoresPanel));
        panelCentral.add(crearPanelConTitulo("Actuadores", actuadoresPanel));
        panelCentral.add(crearPanelConTitulo("Reglas", reglasPanel));

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));

        JPanel panelManual = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actuadorCombo = new JComboBox<>();
        accionCombo = new JComboBox<>();
        ejecutarManualButton = new JButton("Ejecutar acción manual");

        panelManual.add(new JLabel("Actuador:"));
        panelManual.add(actuadorCombo);
        panelManual.add(new JLabel("Acción:"));
        panelManual.add(accionCombo);
        panelManual.add(ejecutarManualButton);

        logArea = new JTextArea(8, 80);
        logArea.setEditable(false);

        panelInferior.add(panelManual, BorderLayout.NORTH);
        panelInferior.add(new JScrollPane(logArea), BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanelConTitulo(String titulo, JPanel contenido) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.add(new JScrollPane(contenido), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Asigna el controlador a la vista y conecta los eventos de usuario.
     *
     * @param controlador controlador principal.
     */
    public void setControlador(SmartTecnoHouseController controlador) {
        this.controlador = controlador;

        actualizarSensoresButton.addActionListener(e -> controlador.actualizarSensores());
        aplicarReglasButton.addActionListener(e -> controlador.aplicarReglas());
        ejecutarManualButton.addActionListener(e -> controlador.ejecutarAccionManual());
        guardarEstadoButton.addActionListener(e -> controlador.guardarEstado());
        cargarEstadoButton.addActionListener(e -> controlador.cargarEstado());

        actuadorCombo.addActionListener(e -> controlador.actualizarAccionesActuador());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.guardarEstadoAlSalir();
            }
        });
    }

    /**
     * Refresca todos los paneles de la interfaz con el estado actual del modelo.
     *
     * @param sistema modelo principal.
     */
    public void refrescar(SmartTecnoHouse sistema) {
        refrescarSensores(sistema);
        refrescarActuadores(sistema);
        refrescarReglas(sistema);
        refrescarLog(sistema);

        revalidate();
        repaint();
    }

    private void refrescarSensores(SmartTecnoHouse sistema) {
        sensoresPanel.removeAll();

        for (Sensor sensor : sistema.getSensores()) {
            JLabel label = new JLabel(
                    sensor.getID() + " - " + sensor.getNombre() + ": " + sensor.getEstadoActual()
            );
            sensoresPanel.add(label);
        }
    }

    private void refrescarActuadores(SmartTecnoHouse sistema) {
        String seleccionado = getActuadorSeleccionado();

        actuadoresPanel.removeAll();
        actuadorCombo.removeAllItems();

        for (Actuador actuador : sistema.getActuadores()) {
            JLabel label = new JLabel(
                    actuador.getID() + " - " + actuador.getNombre() + ": " + actuador.getEstadoActual()
            );
            actuadoresPanel.add(label);
            actuadorCombo.addItem(actuador.getID());
        }

        if (seleccionado != null) {
            actuadorCombo.setSelectedItem(seleccionado);
        }

        Actuador actuador = sistema.buscarActuador(getActuadorSeleccionado());
        actualizarComboAcciones(actuador);
    }

    private void refrescarReglas(SmartTecnoHouse sistema) {
        reglasPanel.removeAll();

        for (Regla regla : sistema.getReglas()) {
            JCheckBox checkBox = new JCheckBox(
                    regla.getId() + " - " + regla.getNombre(),
                    regla.isActiva()
            );

            checkBox.addActionListener(e -> {
                if (controlador != null) {
                    controlador.cambiarEstadoRegla(regla.getId(), checkBox.isSelected());
                }
            });

            reglasPanel.add(checkBox);
        }
    }

    private void refrescarLog(SmartTecnoHouse sistema) {
        StringBuilder sb = new StringBuilder();

        for (String linea : sistema.getActuatorLog()) {
            sb.append(linea).append(System.lineSeparator());
        }

        logArea.setText(sb.toString());
    }

    /**
     * Actualiza las acciones disponibles según el actuador seleccionado.
     *
     * @param actuador actuador seleccionado.
     */
    public void actualizarComboAcciones(Actuador actuador) {
        accionCombo.removeAllItems();

        if (actuador == null) {
            return;
        }

        for (String accion : actuador.getAccionesPosibles()) {
            accionCombo.addItem(accion);
        }
    }

    /**
     * Devuelve el ID del actuador seleccionado en la GUI.
     *
     * @return ID de actuador.
     */
    public String getActuadorSeleccionado() {
        Object item = actuadorCombo.getSelectedItem();
        return item == null ? null : item.toString();
    }

    /**
     * Devuelve la acción seleccionada en la GUI.
     *
     * @return acción seleccionada.
     */
    public String getAccionSeleccionada() {
        Object item = accionCombo.getSelectedItem();
        return item == null ? null : item.toString();
    }

    /**
     * Muestra un mensaje informativo.
     *
     * @param mensaje texto del mensaje.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de error.
     *
     * @param mensaje texto del error.
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
