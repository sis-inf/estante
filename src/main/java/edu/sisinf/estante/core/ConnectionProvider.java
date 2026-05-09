package edu.sisinf.estante.core;

import edu.sisinf.estante.config.DBConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Proveedor de conexiones JDBC.
 * Abre conexiones a partir de un {@link DBConfig}.
 */
public class ConnectionProvider {

    /**
     * Abre una conexión JDBC usando la configuración dada.
     *
     * @param config Configuración de conexión
     * @return Conexión JDBC activa
     * @throws SQLException Si no se puede establecer la conexión
     */
    public static Connection abrir(DBConfig config) throws SQLException {
        try {
            Class.forName(config.getDriver());
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado: " + config.getDriver(), e);
        }
        return DriverManager.getConnection(
                config.getUrl(),
                config.getUsuario(),
                config.getPassword()
        );
    }
}
