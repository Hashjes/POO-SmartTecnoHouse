import controlador.SmartTecnoHouseController;
import modelo.PersistenciaService;
import modelo.SmartTecnoHouse;
import vista.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación Smart TecnoHouse.
 */
public class Main {

    /**
     * Método principal de ejecución.
     *
     * @param args argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmartTecnoHouse modelo = new SmartTecnoHouse();
            PersistenciaService persistenciaService = new PersistenciaService("data/state.json");
            MainFrame vista = new MainFrame();

            SmartTecnoHouseController controller =
                    new SmartTecnoHouseController(modelo, vista, persistenciaService);

            controller.iniciar();
        });
    }
}