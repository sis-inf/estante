```java id="l8fq6h"
package edu.sisinf.estante.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HistorialConsultas {

    private static final int CAPACIDAD_DEFAULT = 50;

    private final int capacidad;
    private final LinkedList<String> historial;

    public HistorialConsultas() {
        this(CAPACIDAD_DEFAULT);
    }

    public HistorialConsultas(int capacidad) {
        this.capacidad = capacidad;
        this.historial = new LinkedList<>();
    }

    public void agregar(String sql) {
        historial.addFirst(sql);

        if (historial.size() > capacidad) {
            historial.removeLast();
        }
    }

    public List<String> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public void limpiar() {
        historial.clear();
    }

    public List<String> buscar(String texto) {
        String textoLower = texto.toLowerCase();

        return historial.stream()
                .filter(query -> query.toLowerCase().contains(textoLower))
                .collect(Collectors.toList());
    }
}
```
