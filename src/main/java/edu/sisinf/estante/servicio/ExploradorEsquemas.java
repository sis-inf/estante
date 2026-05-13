package edu.sisinf.estante.servicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import edu.sisinf.estante.modelo.Esquema;
import edu.sisinf.estante.modelo.Tabla;
import edu.sisinf.estante.modelo.Columna;
import edu.sisinf.estante.modelo.excepcion.ErrorQuery;

/**
 * Servicio stateless para explorar la estructura de la base de datos.
 */
public class ExploradorEsquemas {

    /**
     * Lista los esquemas (catálogos) visibles en la conexión.
     */
    public List<Esquema> listarEsquemas(Connection conexion) {
        List<Esquema> esquemas = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            String url = metaData.getURL();

            // Criterio: Detección de SQLite
            if (url != null && url.startsWith("jdbc:sqlite:")) {
                esquemas.add(new Esquema("main"));
            } else {
                // Criterio: Otros motores (MySQL) usando TABLE_CAT
                try (ResultSet rs = metaData.getCatalogs()) {
                    while (rs.next()) {
                        String nombreEsquema = rs.getString("TABLE_CAT");
                        if (nombreEsquema != null) {
                            esquemas.add(new Esquema(nombreEsquema));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new ErrorQuery("No se pudieron listar los esquemas", e);
        }
        return esquemas;
    }

    /**
     * Lista las tablas dentro del esquema indicado.
     */
    public List<Tabla> listarTablas(Connection conexion, String esquema) {
        List<Tabla> tablas = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            // Criterio: Usar getTables con tipo "TABLE"
            try (ResultSet rs = metaData.getTables(esquema, null, "%", new String[]{"TABLE"})) {
                while (rs.next()) {
                    String nombreTabla = rs.getString("TABLE_NAME");
                    tablas.add(new Tabla(nombreTabla, esquema));
                }
            }
        } catch (SQLException e) {
            throw new ErrorQuery("No se pudieron listar las tablas del esquema " + esquema, e);
        }
        return tablas;
    }

    /**
     * Lista las columnas de la tabla indicada, marcando la Primary Key.
     */
    public List<Columna> listarColumnas(Connection conexion, String esquema, String tabla) {
        List<Columna> columnas = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            
            // Criterio: Obtener Primary Keys para comparar
            List<String> pks = new ArrayList<>();
            try (ResultSet rsPk = metaData.getPrimaryKeys(esquema, null, tabla)) {
                while (rsPk.next()) {
                    pks.add(rsPk.getString("COLUMN_NAME"));
                }
            }

            // Criterio: Obtener columnas y sus metadatos
            try (ResultSet rs = metaData.getColumns(esquema, null, tabla, "%")) {
                while (rs.next()) {
                    String nombreCol = rs.getString("COLUMN_NAME");
                    String tipoSql = rs.getString("TYPE_NAME");
                    boolean esNullable = "YES".equals(rs.getString("IS_NULLABLE"));
                    boolean esPrimaryKey = pks.contains(nombreCol);
                    
                    columnas.add(new Columna(nombreCol, tipoSql, esNullable, esPrimaryKey));
                }
            }
        } catch (SQLException e) {
            throw new ErrorQuery("No se pudieron listar las columnas de la tabla " + tabla, e);
        }
        return columnas;
    }
}