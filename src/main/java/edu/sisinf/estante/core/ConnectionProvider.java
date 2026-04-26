package edu.sisinf.estante.core;

import edu.sisinf.estante.config.DBConfig;
import java.sql.SQLException;

public interface ConnectionProvider {

    void open(DBConfig config) throws SQLException;

    void close() throws SQLException;

    boolean isActive();
}
