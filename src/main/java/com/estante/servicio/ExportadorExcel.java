package com.estante.servicio;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

/**
 * Exportador de resultados SQL a archivos Excel en formato XLSX.
 */
public class ExportadorExcel {

    /**
     * Exporta un ResultSet a un archivo XLSX.
     *
     * @param rs   resultado de la consulta
     * @param ruta ruta del archivo XLSX a generar
     * @throws SQLException si ocurre un error leyendo el ResultSet
     * @throws IOException  si ocurre un error escribiendo el archivo
     */
    public void exportar(ResultSet rs, Path ruta) throws SQLException, IOException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnas = metaData.getColumnCount();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet hoja = workbook.createSheet("Datos");

            CellStyle estiloCabecera = crearEstiloCabecera(workbook);
            CellStyle estiloEntero = crearEstiloEntero(workbook);
            CellStyle estiloDecimal = crearEstiloDecimal(workbook);
            CellStyle estiloFecha = crearEstiloFecha(workbook);
            CellStyle estiloHora = crearEstiloHora(workbook);
            CellStyle estiloFechaHora = crearEstiloFechaHora(workbook);

            crearCabeceras(hoja, metaData, columnas, estiloCabecera);
            crearFilasDatos(
                    hoja,
                    rs,
                    metaData,
                    columnas,
                    estiloEntero,
                    estiloDecimal,
                    estiloFecha,
                    estiloHora,
                    estiloFechaHora
            );

            for (int i = 0; i < columnas; i++) {
                hoja.autoSizeColumn(i);
            }

            Path carpetaPadre = ruta.getParent();
            if (carpetaPadre != null) {
                Files.createDirectories(carpetaPadre);
            }

            try (OutputStream salida = Files.newOutputStream(ruta)) {
                workbook.write(salida);
            }
        }
    }

    private void crearCabeceras(
            Sheet hoja,
            ResultSetMetaData metaData,
            int columnas,
            CellStyle estiloCabecera
    ) throws SQLException {
        Row filaCabecera = hoja.createRow(0);

        for (int i = 1; i <= columnas; i++) {
            Cell celda = filaCabecera.createCell(i - 1);
            celda.setCellValue(metaData.getColumnLabel(i));
            celda.setCellStyle(estiloCabecera);
        }
    }

    private void crearFilasDatos(
            Sheet hoja,
            ResultSet rs,
            ResultSetMetaData metaData,
            int columnas,
            CellStyle estiloEntero,
            CellStyle estiloDecimal,
            CellStyle estiloFecha,
            CellStyle estiloHora,
            CellStyle estiloFechaHora
    ) throws SQLException {
        int numeroFila = 1;

        while (rs.next()) {
            Row fila = hoja.createRow(numeroFila++);

            for (int i = 1; i <= columnas; i++) {
                Cell celda = fila.createCell(i - 1);
                int tipoSql = metaData.getColumnType(i);

                escribirCelda(
                        celda,
                        rs,
                        i,
                        tipoSql,
                        estiloEntero,
                        estiloDecimal,
                        estiloFecha,
                        estiloHora,
                        estiloFechaHora
                );
            }
        }
    }

    private void escribirCelda(
            Cell celda,
            ResultSet rs,
            int indiceColumna,
            int tipoSql,
            CellStyle estiloEntero,
            CellStyle estiloDecimal,
            CellStyle estiloFecha,
            CellStyle estiloHora,
            CellStyle estiloFechaHora
    ) throws SQLException {
        Object valor = rs.getObject(indiceColumna);

        if (valor == null) {
            celda.setBlank();
            return;
        }

        switch (tipoSql) {
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
            case Types.BIGINT:
                long entero = rs.getLong(indiceColumna);
                if (rs.wasNull()) {
                    celda.setBlank();
                } else {
                    celda.setCellValue(entero);
                    celda.setCellStyle(estiloEntero);
                }
                break;

            case Types.FLOAT:
            case Types.REAL:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.DECIMAL:
                BigDecimal decimal = rs.getBigDecimal(indiceColumna);
                if (decimal == null) {
                    celda.setBlank();
                } else {
                    celda.setCellValue(decimal.doubleValue());
                    celda.setCellStyle(estiloDecimal);
                }
                break;

            case Types.DATE:
                Date fecha = rs.getDate(indiceColumna);
                if (fecha == null) {
                    celda.setBlank();
                } else {
                    celda.setCellValue(fecha);
                    celda.setCellStyle(estiloFecha);
                }
                break;

            case Types.TIME:
            case Types.TIME_WITH_TIMEZONE:
                Time hora = rs.getTime(indiceColumna);
                if (hora == null) {
                    celda.setBlank();
                } else {
                    celda.setCellValue(hora);
                    celda.setCellStyle(estiloHora);
                }
                break;

            case Types.TIMESTAMP:
            case Types.TIMESTAMP_WITH_TIMEZONE:
                Timestamp fechaHora = rs.getTimestamp(indiceColumna);
                if (fechaHora == null) {
                    celda.setBlank();
                } else {
                    celda.setCellValue(fechaHora);
                    celda.setCellStyle(estiloFechaHora);
                }
                break;

            case Types.BOOLEAN:
            case Types.BIT:
                boolean booleano = rs.getBoolean(indiceColumna);
                if (rs.wasNull()) {
                    celda.setBlank();
                } else {
                    celda.setCellValue(booleano);
                }
                break;

            default:
                celda.setCellValue(String.valueOf(valor));
                break;
        }
    }

    private CellStyle crearEstiloCabecera(Workbook workbook) {
        Font fuente = workbook.createFont();
        fuente.setBold(true);

        CellStyle estilo = workbook.createCellStyle();
        estilo.setFont(fuente);

        return estilo;
    }

    private CellStyle crearEstiloEntero(Workbook workbook) {
        DataFormat formato = workbook.createDataFormat();

        CellStyle estilo = workbook.createCellStyle();
        estilo.setDataFormat(formato.getFormat("0"));

        return estilo;
    }

    private CellStyle crearEstiloDecimal(Workbook workbook) {
        DataFormat formato = workbook.createDataFormat();

        CellStyle estilo = workbook.createCellStyle();
        estilo.setDataFormat(formato.getFormat("#,##0.00"));

        return estilo;
    }

    private CellStyle crearEstiloFecha(Workbook workbook) {
        CreationHelper helper = workbook.getCreationHelper();

        CellStyle estilo = workbook.createCellStyle();
        estilo.setDataFormat(helper.createDataFormat().getFormat("yyyy-mm-dd"));

        return estilo;
    }

    private CellStyle crearEstiloHora(Workbook workbook) {
        CreationHelper helper = workbook.getCreationHelper();

        CellStyle estilo = workbook.createCellStyle();
        estilo.setDataFormat(helper.createDataFormat().getFormat("hh:mm:ss"));

        return estilo;
    }

    private CellStyle crearEstiloFechaHora(Workbook workbook) {
        CreationHelper helper = workbook.getCreationHelper();

        CellStyle estilo = workbook.createCellStyle();
        estilo.setDataFormat(helper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));

        return estilo;
    }
}