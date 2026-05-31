package edu.sisinf.estante.dao;

import edu.sisinf.estante.modelo.Conexion;
import edu.sisinf.estante.modelo.TipoMotor;
import edu.sisinf.estante.core.ErrorConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementación concreta de {@link IConexionDAO} para SQLite.
 * SQLite es una base de datos embebida que se accede vía un archivo
 * {@code .db} en disco. No requiere host, puerto, usuario ni password.
 * La clase es stateless: no almacena conexiones internamente.
 */
public class ConexionDAOSQLite implements IConexionDAO {

    /**
     * Devuelve el motor de base de datos que maneja esta implementación.
     *
     * @return {@link TipoMotor#SQLITE}
     */
    @Override
    public TipoMotor motor() {
        return TipoMotor.SQLITE;
    }

    /**
     * Construye la URL JDBC para SQLite con el formato
     * {@code jdbc:sqlite:<ruta>}, donde {@code <ruta>} es el valor
     * del campo {@code basedatos} del objeto {@link Conexion}.
     *
     * @param conexion Objeto con los datos de conexión.
     * @return URL JDBC para SQLite.
     */
    @Override
    public String construirUrl(Conexion conexion) {
        return "jdbc:sqlite:" + conexion.getBasedatos();
    }

    /**
     * Abre una conexión a la base de datos SQLite.
     *
     * @param conexion Objeto con los datos de conexión.
     * @return Conexión JDBC activa.
     * @throws ErrorConexion si el motor no es SQLite o la ruta está vacía.
     * @throws SQLException  si el driver falla al conectar.
     */
    @Override
    public Connection abrir(Conexion conexion) throws ErrorConexion, SQLException {

        if (conexion.getTipoMotor() != TipoMotor.SQLITE) {
            throw new ErrorConexion("El motor de la conexión no es SQLite.");
        }

        if (conexion.getBasedatos() == null || conexion.getBasedatos().isBlank()) {
            throw new ErrorConexion("La ruta de la base de datos no puede estar vacía.");
        }

        String url = construirUrl(conexion);
        return DriverManager.getConnection(url);
    }

    /**
     * Prueba si la conexión a la base de datos SQLite es válida.
     *
     * @param conexion Objeto con los datos de conexión.
     * @return {@code true} si la conexión es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean probar(Conexion conexion) {
        try (Connection conn = abrir(conexion)) {
            return conn.isValid(2);
        } catch (Exception e) {
            return false;
        }
    }
}