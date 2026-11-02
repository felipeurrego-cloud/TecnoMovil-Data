
package ServicioDeTransito;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import TecnoViaje.Registro;

public class RegistroDeMovibilidad {

    // a) Calcular la afluencia por estación
    public Map<String, Long> calcularFrecuenciaPorEstacion(List<Registro> registros) {

        Map<String, Long> resultado = new HashMap<>();

        registros.stream()
                .filter(r -> r.getAccion().equalsIgnoreCase("entrada"))
                .forEach(r -> {

                    String estacion = r.getEstacion();

                    if (resultado.containsKey(estacion)) {
                        resultado.put(estacion, resultado.get(estacion) + 1);
                    } else {
                        resultado.put(estacion, 1L);
                    }
                });

        return resultado;
    }

    // b) Identificar las horas pico
    public Map<Integer, Long> identificarHorasPico(List<Registro> registros) {

        Map<Integer, Long> resultado = new HashMap<>();

        registros.stream().forEach(r -> {

            int hora = r.getTimestamp().getHour();

            if (resultado.containsKey(hora)) {
                resultado.put(hora, resultado.get(hora) + 1);
            } else {
                resultado.put(hora, 1L);
            }
        });

        return resultado;
    }

    // c) Calcular las rutas más utilizadas
    public Map<String, Long> calcularRutasMasUtilizadas(List<Registro> registros) {

        Map<String, Long> resultado = new HashMap<>();

        registros.stream().forEach(r -> {

            String ruta = r.getRuta();

            if (resultado.containsKey(ruta)) {
                resultado.put(ruta, resultado.get(ruta) + 1);
            } else {
                resultado.put(ruta, 1L);
            }
        });

        return resultado;
    }

    // d) Obtener los patrones de viaje por usuario
    public Map<String, List<String>> obtenerPatronesViajePorUsuario(List<Registro> registros) {

        Map<String, List<String>> resultado = new HashMap<>();

        registros.stream()
                .sorted(Comparator.comparing(r -> r.getTimestamp()))
                .forEach(r -> {

                    String usuario = r.getIdUsuario();

                    if (!resultado.containsKey(usuario)) {
                        resultado.put(usuario, new ArrayList<>());
                    }

                    resultado.get(usuario).add(r.getEstacion());
                });

        return resultado;
    }

    // e) Calcular el tiempo promedio entre estaciones
    public double calcularTiempoPromedioEntreEstaciones(List<Registro> registros) {

        Map<String, List<Registro>> usuarios = new HashMap<>();

        registros.stream().forEach(r -> {

            String usuario = r.getIdUsuario();

            if (!usuarios.containsKey(usuario)) {
                usuarios.put(usuario, new ArrayList<>());
            }

            usuarios.get(usuario).add(r);
        });

        long sumaTiempos = 0;
        int cantidadViajes = 0;

        for (List<Registro> lista : usuarios.values()) {

            lista.sort(Comparator.comparing(r -> r.getTimestamp()));

            for (int i = 0; i < lista.size() - 1; i++) {

                if (lista.get(i).getAccion().equalsIgnoreCase("entrada")
                        && lista.get(i + 1).getAccion().equalsIgnoreCase("salida")) {

                    sumaTiempos = sumaTiempos + Duration.between(
                            lista.get(i).getTimestamp(),
                            lista.get(i + 1).getTimestamp()
                    ).toMinutes();

                    cantidadViajes = cantidadViajes + 1;
                }
            }
        }

        if (cantidadViajes == 0) {
            return 0;
        }

        return (double) sumaTiempos / cantidadViajes;
    }

    // f) Detectar rutas con sobrecarga
    public List<String> detectarRutasCriticas(List<Registro> registros, long umbralSobrecarga) {

        Map<String, Long> rutas = new HashMap<>();

        registros.stream().forEach(r -> {

            String ruta = r.getRuta();

            if (rutas.containsKey(ruta)) {
                rutas.put(ruta, rutas.get(ruta) + 1);
            } else {
                rutas.put(ruta, 1L);
            }
        });

        List<String> rutasCriticas = new ArrayList<>();

        rutas.forEach((ruta, cantidad) -> {

            if (cantidad > umbralSobrecarga) {
                rutasCriticas.add(ruta);
            }
        });

        return rutasCriticas;
    }
}
