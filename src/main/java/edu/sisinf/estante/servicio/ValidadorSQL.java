package edu.sisinf.estante.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio encargado de validar la sintaxis basica y los permisos de consultas
 * SQL antes de su ejecucion en el servidor.
 */
public class ValidadorSQL {

  private static final Pattern SELECT_PURO_PATTERN =
      Pattern.compile("^\\s*(SELECT|WITH)\\b", Pattern.CASE_INSENSITIVE);

  private static final Pattern DML_PATTERN = Pattern.compile(
      "\\b(INSERT|UPDATE|DELETE|DROP|TRUNCATE)\\b", Pattern.CASE_INSENSITIVE);

  private static final Pattern SELECT_SINTAXIS_PATTERN = Pattern.compile(
      "^\\s*SELECT\\s+.+\\s+FROM\\s+.+", Pattern.CASE_INSENSITIVE);

  /**
   * Verifica si la consulta es unicamente de lectura.
   */
  public boolean esSelectPuro(String sql) {
    if (sql == null || sql.isBlank()) {
      return false;
    }
    return SELECT_PURO_PATTERN.matcher(sql).find();
  }

  /**
   * Detecta si la consulta contiene comandos que alteran o eliminan datos.
   */
  public boolean contieneDML(String sql) {
    if (sql == null || sql.isBlank()) {
      return false;
    }
    return DML_PATTERN.matcher(sql).find();
  }

  /**
   * Realiza un analisis de sintaxis basica sobre la consulta.
   */
  public List<String> validar(String sql) {
    List<String> errores = new ArrayList<>();

    if (sql == null || sql.isBlank()) {
      errores.add("La consulta SQL no puede estar vacia.");
      return errores;
    }

    String sqlTrimmed = sql.trim();

    if (sqlTrimmed.toUpperCase().startsWith("SELECT")) {
      if (!SELECT_SINTAXIS_PATTERN.matcher(sqlTrimmed).find()) {
        errores.add("Error de sintaxis: Estructura SELECT incompleta.");
      }
    }

    if (contieneDML(sqlTrimmed)) {
      errores.add(
          "Seguridad: No se permiten operaciones DML en este contexto.");
    }

    return errores;
  }
}