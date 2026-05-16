package edu.sisinf.estante.core;

import edu.sisinf.estante.config.DBConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import edu.sisinf.estante.dto.QueryResult;
import edu.sisinf.estante.util.SqlValidator;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import edu.sisinf.estante.util.SqlValidator;
import java.sql.Statement;

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
    /**
 * Ejecuta una sentencia SELECT y retorna el resultado encapsulado en un {@link QueryResult}.
 *
 * <p>Los recursos ({@link Statement} y {@link ResultSet}) se cierran en el bloque
 * {@code finally} para garantizar su liberación incluso si ocurre un error.</p>
 *
 * @param connection conexión JDBC activa sobre la que se ejecuta la consulta
 * @param sql        sentencia SQL de lectura (debe ser SELECT)
 * @return {@link QueryResult} con las columnas y filas del resultado
 * @throws IllegalArgumentException si la sentencia no es un SELECT
 * @throws ErrorQuery               si ocurre un error durante la ejecución SQL
 */
public static QueryResult executeSelect(Connection connection, String sql) {
    if (!SqlValidator.esLectura(sql)) {
        throw new IllegalArgumentException(
                "executeSelect() solo acepta sentencias SELECT. Use executeUpdate() para escrituras."
        );
    }

    Statement statement = null;
    ResultSet resultSet = null;

    try {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();

        List<String> columns = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columns.add(metadata.getColumnName(i));
        }

        List<List<Object>> rows = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getObject(i));
            }
            rows.add(row);
        }

        return QueryResult.of(columns, rows);

    } catch (SQLException e) {
        throw new ErrorQuery("Error al ejecutar la sentencia SELECT: " + e.getMessage(), e);
    } finally {
        if (resultSet != null) {
            try { resultSet.close(); } catch (SQLException ignored) {}
        }
        if (statement != null) {
            try { statement.close(); } catch (SQLException ignored) {}
        }
    }
}
}
 * Ejecuta una sentencia de escritura (INSERT, UPDATE, DELETE) y retorna
 * el número de filas afectadas.
 *
 * <p>Este método rechaza explícitamente sentencias SELECT, ya que está
 * diseñado exclusivamente para operaciones de modificación de datos.</p>
 *
 * @param connection conexión JDBC activa sobre la que se ejecuta la sentencia
 * @param sql        sentencia SQL de escritura (INSERT, UPDATE o DELETE)
 * @return número de filas afectadas por la sentencia
 * @throws IllegalArgumentException si la sentencia es un SELECT
 * @throws ErrorQuery               si ocurre un error durante la ejecución SQL
 */
public static int executeUpdate(Connection connection, String sql) {
    if (SqlValidator.esLectura(sql)) {
        throw new IllegalArgumentException(
                "executeUpdate() no acepta sentencias SELECT. Use executeQuery() para lecturas."
        );
    }

    try (Statement statement = connection.createStatement()) {
        return statement.executeUpdate(sql);
    } catch (SQLException e) {
        throw new ErrorQuery("Error al ejecutar la sentencia de escritura: " + e.getMessage(), e);
        }
    }
}
