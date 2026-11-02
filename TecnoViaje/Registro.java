
package TecnoViaje;

import java.time.LocalDateTime;

public class Registro {

    private final String idUsuario;
    private final String ruta;
    private final String estacion;
    private final String accion;
    private final LocalDateTime timestamp;

    public Registro(String idUsuario, String ruta, String estacion,
                    String accion, LocalDateTime timestamp) {

        this.idUsuario = idUsuario;
        this.ruta = ruta;
        this.estacion = estacion;
        this.accion = accion;
        this.timestamp = timestamp;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getRuta() {
        return ruta;
    }

    public String getEstacion() {
        return estacion;
    }

    public String getAccion() {
        return accion;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
