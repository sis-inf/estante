package edu.sisinf.estante.dao;

import edu.sisinf.estante.modelo.Conexion;
import edu.sisinf.estante.modelo.TipoMotor;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstrae las particularidades de cada motor de base de datos (SQLite, MySQL, etc.)
 * detrás de una interfaz común para la apertura de conexiones JDBC.
 *
 * <p>Cada motor soportado debe tener exactamente una implementación de esta interfaz.
 * El sistema la usa a través de un registro o fábrica que selecciona la implementación
 * correcta según el {@link TipoMotor} de la {@link Conexion} recibida.</p>
 *
 * <p>Uso típico:</p>
 * <pre>{@code
 * IConexionDAO dao = ...;          // SQLiteConexionDAO, MySQLConexionDAO, etc.
 * String url = dao.construirUrl(conexion);
 * try (Connection conn = dao.abrir(conexion)) {
 *     // trabajar con la conexión
 * }
 * }</pre>
 */
public interface IConexionDAO {

    /**
     * Indica el motor de base de datos que esta implementación maneja.
     *
     * <p>El valor devuelto debe ser constante para toda la vida del objeto y
     * coincidir con el motor configurado en las instancias de {@link Conexion}
     * que se le pasarán a los demás métodos.</p>
     *
     * @return el {@link TipoMotor} que gestiona esta implementación
     *         (p. ej. {@code TipoMotor.SQLITE} o {@code TipoMotor.MYSQL})
     */
    TipoMotor motor();

    /**
     * Construye la URL JDBC específica del motor a partir de los datos de conexión.
     *
     * <p>Cada motor tiene su propio esquema de URL JDBC:</p>
     * <ul>
     *   <li>SQLite:  {@code jdbc:sqlite:/ruta/al/archivo.db}</li>
     *   <li>MySQL:   {@code jdbc:mysql://host:puerto/base_de_datos}</li>
     * </ul>
     *
     * @param conexion objeto con los parámetros de conexión (host, puerto, base de datos,
     *                 ruta de archivo, etc.); no debe ser {@code null}
     * @return la URL JDBC completa y lista para usarse con {@link java.sql.DriverManager}
     */
    String construirUrl(Conexion conexion);

    /**
     * Abre y devuelve una conexión JDBC activa al motor de base de datos.
     *
     * <p>El llamador es responsable de cerrar la conexión cuando ya no la necesite,
     * preferiblemente usando un bloque {@code try-with-resources}.</p>
     *
     * @param conexion objeto con los parámetros de conexión; no debe ser {@code null}
     * @return una {@link Connection} JDBC lista para ejecutar sentencias SQL
     * @throws SQLException si el driver no puede establecer la conexión
     *                      (credenciales incorrectas, servidor inalcanzable, etc.)
     */
    Connection abrir(Conexion conexion) throws SQLException;

    /**
     * Verifica que los parámetros de conexión sean válidos intentando abrir y cerrar
     * una conexión real al motor.
     *
     * <p>Este método captura cualquier excepción internamente; nunca lanza.</p>
     *
     * @param conexion objeto con los parámetros de conexión a probar; no debe ser {@code null}
     * @return {@code true} si la conexión pudo abrirse y cerrarse sin errores;
     *         {@code false} en cualquier otro caso
     */
    boolean probar(Conexion conexion);
}
