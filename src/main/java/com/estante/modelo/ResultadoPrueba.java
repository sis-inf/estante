package com.estante.modelo;

// Definimos el resultado solicitado
public record ResultadoPrueba(
    boolean exitosa,
    long tiempoMs,
    String mensajeError
) {}

// Definimos el modelo de Conexión en el mismo archivo para no crear archivos extra
record Conexion(
    String url,
    String usuario,
    String contrasena
) {
    public String getUrl() { return url; }
    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
}