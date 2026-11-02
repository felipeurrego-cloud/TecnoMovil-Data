
package FuncionFinal;

import java.time.LocalDateTime;
import java.util.List;

import TecnoViaje.Registro;
import ServicioDeTransito.RegistroDeMovibilidad;

public class TecnoViajeapp {

    public static void main(String[] args) {

        // Crear registros de prueba
        List<Registro> registros = List.of(

            new Registro("U01", "Ruta 1", "Estacion Norte", "entrada",
                    LocalDateTime.of(2026, 10, 20, 7, 0)),

            new Registro("U01", "Ruta 1", "Estacion Centro", "salida",
                    LocalDateTime.of(2026, 10, 20, 7, 20)),

            new Registro("U02", "Ruta 2", "Estacion Sur", "entrada",
                    LocalDateTime.of(2026, 10, 20, 8, 0)),

            new Registro("U02", "Ruta 2", "Estacion Centro", "salida",
                    LocalDateTime.of(2026, 10, 20, 8, 30)),

            new Registro("U03", "Ruta 1", "Estacion Norte", "entrada",
                    LocalDateTime.of(2026, 10, 20, 8, 15)),

            new Registro("U03", "Ruta 1", "Estacion Sur", "salida",
                    LocalDateTime.of(2026, 10, 20, 8, 45)),

            new Registro("U04", "Ruta 3", "Estacion Centro", "entrada",
                    LocalDateTime.of(2026, 10, 20, 9, 0)),

            new Registro("U04", "Ruta 3", "Estacion Norte", "salida",
                    LocalDateTime.of(2026, 10, 20, 9, 25)),

            new Registro("U05", "Ruta 1", "Estacion Sur", "entrada",
                    LocalDateTime.of(2026, 10, 20, 9, 30)),

            new Registro("U05", "Ruta 1", "Estacion Centro", "salida",
                    LocalDateTime.of(2026, 10, 20, 10, 0))
        );

        // Crear el servicio
        RegistroDeMovibilidad servicio = new RegistroDeMovibilidad();

        // Mostrar resultados
        System.out.println("=== SISTEMA TECNOMOVIL DATA ===");

        System.out.println("\na) Afluencia por estacion:");
        System.out.println(servicio.calcularFrecuenciaPorEstacion(registros));

        System.out.println("\nb) Horas pico:");
        System.out.println(servicio.identificarHorasPico(registros));

        System.out.println("\nc) Rutas mas utilizadas:");
        System.out.println(servicio.calcularRutasMasUtilizadas(registros));

        System.out.println("\nd) Patrones de viaje por usuario:");
        System.out.println(servicio.obtenerPatronesViajePorUsuario(registros));

        System.out.println("\ne) Tiempo promedio entre estaciones:");
        System.out.println(servicio.calcularTiempoPromedioEntreEstaciones(registros) + " minutos");

        System.out.println("\nf) Rutas con sobrecarga:");
        System.out.println(servicio.detectarRutasCriticas(registros, 3));
    }
}
