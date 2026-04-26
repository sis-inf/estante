package com.sisinf.estante.config;

/**
 * Configuración de conexión a base de datos.
 *
 * @param host Dirección del servidor
 * @param puerto Puerto de conexión
 * @param nombre Nombre de la base de datos
 * @param usuario Usuario de acceso
 * @param password Contraseña
 */
public record DBConfig(
        String host,
        int puerto,
        String nombre,
        String usuario,
        String password
) {

    public DBConfig {
        if (host == null || host.isBlank())
            throw new IllegalArgumentException("host no puede estar vacío");

        if (puerto <= 0)
            throw new IllegalArgumentException("puerto inválido");

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("nombre no puede estar vacío");

        if (usuario == null || usuario.isBlank())
            throw new IllegalArgumentException("usuario no puede estar vacío");

        if (password == null)
            throw new IllegalArgumentException("password no puede ser nulo");
    }

    /**
     * Genera la URL JDBC para MySQL.
     *
     * @return URL JDBC
     */
    public String toJdbcUrl() {
        return "jdbc:mysql://" + host + ":" + puerto + "/" + nombre;
    }
}