// ============================================================
// ARCHIVO: ValidadorSQL.java
// RUTA: src/main/java/edu/sisinf/estante/util/ValidadorSQL.java
// ============================================================
package edu.sisinf.estante.util;

import java.util.regex.Pattern;

/**
 * Utilidad estática para detectar el tipo de sentencia SQL ingresada por el usuario.
 *
 * <p>Todos los métodos ignoran mayúsculas/minúsculas y espacios iniciales.
 * La detección se realiza mediante expresiones regulares sobre la biblioteca estándar,
 * sin dependencias externas.</p>
 *
 * <p>Uso típico:</p>
 * <pre>
 *   ValidadorSQL.isSelect("SELECT * FROM usuarios"); // true
 *   ValidadorSQL.isDML("delete from t");             // true
 *   ValidadorSQL.esSentenciaValida(null);            // false
 * </pre>
 */
public class ValidadorSQL {

    private static final Pattern PATTERN_SELECT = Pattern.compile("^\\s*SELECT\\b.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern PATTERN_INSERT = Pattern.compile("^\\s*INSERT\\b.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern PATTERN_UPDATE = Pattern.compile("^\\s*UPDATE\\b.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    private static final Pattern PATTERN_DELETE = Pattern.compile("^\\s*DELETE\\b.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    /** Constructor privado para evitar instanciación. */
    private ValidadorSQL() {}

    /**
     * Indica si la sentencia SQL es un SELECT.
     *
     * @param sql sentencia SQL a evaluar
     * @return {@code true} si la sentencia comienza con SELECT
     */
    public static boolean isSelect(String sql) {
        return esSentenciaValida(sql) && PATTERN_SELECT.matcher(sql).matches();
    }

    /**
     * Indica si la sentencia SQL es un INSERT.
     *
     * @param sql sentencia SQL a evaluar
     * @return {@code true} si la sentencia comienza con INSERT
     */
    public static boolean isInsert(String sql) {
        return esSentenciaValida(sql) && PATTERN_INSERT.matcher(sql).matches();
    }

    /**
     * Indica si la sentencia SQL es un UPDATE.
     *
     * @param sql sentencia SQL a evaluar
     * @return {@code true} si la sentencia comienza con UPDATE
     */
    public static boolean isUpdate(String sql) {
        return esSentenciaValida(sql) && PATTERN_UPDATE.matcher(sql).matches();
    }

    /**
     * Indica si la sentencia SQL es un DELETE.
     *
     * @param sql sentencia SQL a evaluar
     * @return {@code true} si la sentencia comienza con DELETE
     */
    public static boolean isDelete(String sql) {
        return esSentenciaValida(sql) && PATTERN_DELETE.matcher(sql).matches();
    }

    /**
     * Indica si la sentencia SQL es una operación DML de escritura (INSERT, UPDATE o DELETE).
     *
     * <p>Útil para mostrar advertencias antes de ejecutar operaciones destructivas.</p>
     *
     * @param sql sentencia SQL a evaluar
     * @return {@code true} si la sentencia es INSERT, UPDATE o DELETE
     */
    public static boolean isDML(String sql) {
        return isInsert(sql) || isUpdate(sql) || isDelete(sql);
    }

    /**
     * Indica si la sentencia SQL es válida (no nula y no vacía).
     *
     * @param sql sentencia SQL a evaluar
     * @return {@code false} si la sentencia es {@code null} o está vacía tras eliminar espacios
     */
    public static boolean esSentenciaValida(String sql) {
        return sql != null && !sql.strip().isEmpty();
    }
}