package com.sisinf.estante.core;

import com.sisinf.estante.config.DBConfig;
import java.sql.SQLException;

/**
 */
public interface ConnectionProvider {

    /**

     *
     * @param config Configuración de conexión
     * @throws SQLException si ocurre un error al conectar
     */
    void open(DBConfig config) throws SQLException;

    /**
     *
     * @throws SQLException 
     */
    void close() throws SQLException;

    /**
     *
     * @return 
     */
    boolean isActive();
}