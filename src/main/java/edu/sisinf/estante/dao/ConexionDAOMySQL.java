package edu.sisinf.estante.dao;

import edu.sisinf.estante.modelo.Conexion;
import edu.sisinf.estante.modelo.TipoMotor;
import edu.sisinf.estante.core.ErrorConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementación concreta de {@link IConexionDAO} para MySQL.
 * MySQL requiere host, puerto, usuario y password para conectarse.
 * La clase es stateless: no almacena conexiones internamente
 * y no implementa pooling de conexiones.
 */
public class ConexionDAOMySQL implements IConexionDAO {

    private static final String PUERTO_DEFAULT = "3306";
    private static final String PARAMS =
            "useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    /**
     * Devuelve el motor de base de datos que maneja esta implementación.
     *
     * @return {@link TipoMotor#MYSQL}
     */
    @Override
    public TipoMotor motor() {
        return TipoMotor.MYSQL;
    }

    /**
     * Construye la URL JDBC para MySQL con el formato
     * {@code jdbc:mysql://<host>:<puerto>/<basedatos>?<parametros>}.
     * Si el puerto es {@code null}, se usa {@code 3306} por defecto.
     *
     * @param conexion Objeto con los datos de conexión.
     * @return URL JDBC para MySQL.
     */
    @Override
    public String construirUrl(Conexion conexion) {
        String puerto = (conexion.getPuerto() != null)
                ? conexion.getPuerto().toString()
                : PUERTO_DEFAULT;

        return String.format("jdbc:mysql://%s:%s/%s?%s",
                conexion.getHost(),
                puerto,
                conexion.getBasedatos(),
                PARAMS);
    }

    /**
     * Abre una conexión a la base de datos MySQL.
     *
     * @param conexion Objeto con los datos de conexión.
     * @return Conexión JDBC activa.
     * @throws ErrorConexion si el motor no es MySQL o faltan campos obligatorios.
     * @throws SQLException  si el driver falla al conectar.
     */
    @Override
    public Connection abrir(Conexion conexion) throws ErrorConexion, SQLException {

        if (conexion.getTipoMotor() != TipoMotor.MYSQL) {
            throw new ErrorConexion("El motor de la conexión no es MySQL.");
        }

        if (conexion.getHost() == null || conexion.getHost().isBlank()) {
            throw new ErrorConexion("El host no puede estar vacío.");
        }

        if (conexion.getBasedatos() == null || conexion.getBasedatos().isBlank()) {
            throw new ErrorConexion("El nombre de la base de datos no puede estar vacío.");
        }

        String url = construirUrl(conexion);
        return DriverManager.getConnection(url,
                conexion.getUsuario(),
                conexion.getPassword());
    }

    /**
     * Prueba si la conexión a la base de datos MySQL es válida.
     *
     * @param conexion Objeto con los datos de conexión.
     * @return {@code true} si la conexión es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean probar(Conexion conexion) {
        try (Connection conn = abrir(conexion)) {
            return conn.isValid(3);
        } catch (Exception e) {
            return false;
        }
    }
}