package edu.sisinf.estante.servicio;

import edu.sisinf.estante.modelo.EntradaHistorial;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Servicio que mantiene un registro de las últimas 50 queries ejecutadas.
 *
 * Usa política FIFO: cuando se supera el límite, la entrada más antigua
 * es descartada automáticamente.
 */
public class HistorialQuerys {

    private static final int LIMITE = 50;

    private final Deque<EntradaHistorial> historial = new ArrayDeque<>();

    /**
     * Agrega una nueva entrada al historial marcada como exitosa.
     *
     * @param query      sentencia SQL ejecutada
     * @param baseDatos  nombre de la base de datos o conexión activa
     * @param duracionMs tiempo de ejecución en milisegundos
     */
    public void agregar(String query, String baseDatos, long duracionMs) {
        agregar(query, baseDatos, duracionMs, true);
    }

    /**
     * Agrega una nueva entrada al historial con indicador de éxito.
     *
     * @param query      sentencia SQL ejecutada
     * @param baseDatos  nombre de la base de datos o conexión activa
     * @param duracionMs tiempo de ejecución en milisegundos
     * @param exitosa    indica si la query se ejecutó sin errores
     */
    public void agregar(String query, String baseDatos, long duracionMs, boolean exitosa) {
        if (historial.size() >= LIMITE) {
            historial.pollFirst();
        }
        historial.addLast(new EntradaHistorial(
                System.currentTimeMillis(),
                query,
                baseDatos,
                duracionMs,
                exitosa
        ));
    }
    /**
     * Compatibilidad con versiones anteriores.
     *
     * @param query consulta SQL registrada
     */
    public void agregar(String query) {
        agregar(query, "", System.currentTimeMillis());
    }

    /**
     * Retorna todas las entradas del historial en orden cronológico.
     *
     * @return lista de entradas del historial
     */
    
    public List<EntradaHistorial> obtenerTodos() {
        return new ArrayList<>(historial);
    }
    /**
    * Retorna la última query agregada al historial.
    *
    * @return texto de la última query o null si está vacío
    */
    public String obtenerUltimo() {
        EntradaHistorial ultima = historial.peekLast();
        return ultima != null ? ultima.query() : null;
    }

    /**
     * Vacía el historial completamente.
     */
    public void limpiar() {
        historial.clear();
    }
    }
