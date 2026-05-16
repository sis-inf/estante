package edu.sisinf.estante.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Esquema {

    private String nombre;
    private List<String> tablas;

    // Constructor
    public Esquema(String nombre) {
        this.nombre = nombre;
        this.tablas = new ArrayList<>();
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Agregar tabla
    public void agregarTabla(String nombre) {
        tablas.add(nombre);
    }

    // Devuelve lista no modificable
    public List<String> getTablas() {
        return Collections.unmodifiableList(tablas);
    }

    // Total de tablas
    public int getTotalTablas() {
        return tablas.size();
    }
}
