package com.sisinf.estante.config;

/**
 *
 * @param host     Dirección del servidor de base de datos
 * @param puerto   Puerto de conexión
 * @param nombre   Nombre de la base de datos
 * @param usuario  Usuario de conexión
 * @param password Contraseña de conexión
 */
public record DBConfig(
        String host,
        int puerto,
        String nombre,
        String usuario,
        String password
) {
    /**
     */
    public DBConfig {
        if (host == null) throw new IllegalArgumentException("host no puede ser nulo");
        if (nombre == null) throw new IllegalArgumentException("nombre no puede ser nulo");
        if (usuario == null) throw new IllegalArgumentException("usuario no puede ser nulo");
        if (password == null) throw new IllegalArgumentException("password no puede ser nulo");
    }

    /**
     *
     * @return 
     */
    public String toJdbcUrl() {
        return "jdbc:mysql://" + host + ":" + puerto + "/" + nombre;
    }
}