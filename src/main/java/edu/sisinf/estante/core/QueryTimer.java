package edu.sisinf.estante.core;

/**
 * Clase utilitaria para medir el tiempo de ejecución de queries.
 * Utiliza System.nanoTime() para mayor precisión.
 */
public class QueryTimer {

    private Long tiempoInicio;
    private Long tiempoFin;

    /**
     * Registra el tiempo de inicio de la medición.
     */
    public void iniciar() {
        tiempoInicio = System.nanoTime();
        tiempoFin = null;
    }

    /**
     * Registra el tiempo de fin de la medición.
     */
    public void detener() {
        tiempoFin = System.nanoTime();
    }

    /**
     * Devuelve la diferencia entre inicio y fin en milisegundos.
     *
     * @return tiempo transcurrido en milisegundos
     * @throws IllegalStateException si se llama antes de invocar detener()
     */
    public double getTiempoMs() {
        if (tiempoFin == null) {
            throw new IllegalStateException(
                "No se puede obtener el tiempo: detener() no ha sido llamado.");
        }
        return (tiempoFin - tiempoInicio) / 1_000_000.0;
    }

    /**
     * Devuelve el tiempo formateado como string.
     * Ejemplos: "123 ms", "1.2 s"
     *
     * @return string con el tiempo formateado
     * @throws IllegalStateException si se llama antes de invocar detener()
     */
    public String getTiempoFormateado() {
        double ms = getTiempoMs();
        if (ms < 1000) {
            return String.format("%.0f ms", ms);
        } else {
            return String.format("%.1f s", ms / 1000.0);
        }
    }
}
