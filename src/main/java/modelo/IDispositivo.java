package modelo;

/**
 * Interfaz común para todos los dispositivos del sistema Smart TecnoHouse.
 * Permite tratar sensores y actuadores de forma polimórfica.
 */
public interface IDispositivo {

    /**
     * Devuelve el identificador único del dispositivo.
     *
     * @return ID del dispositivo.
     */
    String getID();

    /**
     * Devuelve el nombre legible del dispositivo.
     *
     * @return nombre del dispositivo.
     */
    String getNombre();

    /**
     * Devuelve un resumen del estado actual del dispositivo.
     *
     * @return estado actual en formato texto.
     */
    String getEstadoActual();
}