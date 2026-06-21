package com.estante.modelo;

public record ResultadoPrueba(
    boolean exitosa,
    long tiempoMs,
    String mensajeError
) {}

