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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Servicio para exportar resultados de consultas a archivos Excel.
 */
public class ExportadorExcel {

    /**
     * Exporta un ResultSet a un archivo XLSX.
     *
     * @param rs   resultado de la consulta
     * @param ruta ruta del archivo destino
     * @throws SQLException si ocurre un error leyendo el ResultSet
     * @throws IOException  si ocurre un error escribiendo el archivo
     */
    public void exportar(ResultSet rs, Path ruta) throws SQLException, IOException {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Resultados");

            ResultSetMetaData metaData = rs.getMetaData();
            int columnas = metaData.getColumnCount();

            CellStyle estiloCabecera = crearEstiloCabecera(workbook);
            CellStyle estiloFecha = crearEstiloFecha(workbook);
            CellStyle estiloFechaHora = crearEstiloFechaHora(workbook);
            CellStyle estiloHora = crearEstiloHora(workbook);

            Row filaCabecera = sheet.createRow(0);

            for (int c = 1; c <= columnas; c++) {
                Cell celda = filaCabecera.createCell(c - 1);
                celda.setCellValue(obtenerNombreColumna(metaData, c));
                celda.setCellStyle(estiloCabecera);
            }

            int indiceFila = 1;

            while (rs.next()) {

                Row fila = sheet.createRow(indiceFila++);

                for (int c = 1; c <= columnas; c++) {
                    Cell celda = fila.createCell(c - 1);
                    Object valor = rs.getObject(c);
                    int tipo = metaData.getColumnType(c);

                    escribirCelda(
                            celda,
                            valor,
                            tipo,
                            estiloFecha,
                            estiloFechaHora,
                            estiloHora
                    );
                }
            }

            for (int c = 0; c < columnas; c++) {
                sheet.autoSizeColumn(c);
            }

            try (OutputStream salida = Files.newOutputStream(ruta)) {
                workbook.write(salida);
            }
        }
    }

    private String obtenerNombreColumna(
            ResultSetMetaData metaData,
            int columna
    ) throws SQLException {

        String etiqueta = metaData.getColumnLabel(columna);

        if (etiqueta == null || etiqueta.isBlank()) {
            return metaData.getColumnName(columna);
        }

        return etiqueta;
    }

    private void escribirCelda(
            Cell celda,
            Object valor,
            int tipo,
            CellStyle estiloFecha,
            CellStyle estiloFechaHora,
            CellStyle estiloHora
    ) {

        if (valor == null) {
            celda.setBlank();
            return;
        }

        switch (tipo) {
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
            case Types.BIGINT:
                escribirNumero(celda, valor);
                break;

            case Types.FLOAT:
            case Types.REAL:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.DECIMAL:
                escribirNumero(celda, valor);
                break;

            case Types.DATE:
                escribirFecha(celda, valor, estiloFecha);
                break;

            case Types.TIMESTAMP:
            case Types.TIMESTAMP_WITH_TIMEZONE:
                escribirFechaHora(celda, valor, estiloFechaHora);
                break;

            case Types.TIME:
            case Types.TIME_WITH_TIMEZONE:
                escribirHora(celda, valor, estiloHora);
                break;

            case Types.BOOLEAN:
            case Types.BIT:
                escribirBooleano(celda, valor);
                break;

            default:
                escribirValorPorDefecto(celda, valor);
                break;
        }
    }

    private void escribirNumero(Cell celda, Object valor) {

        if (valor instanceof BigDecimal numero) {
            celda.setCellValue(numero.doubleValue());
            return;
        }

        if (valor instanceof Number numero) {
            celda.setCellValue(numero.doubleValue());
            return;
        }

        celda.setCellValue(valor.toString());
    }

    private void escribirFecha(
            Cell celda,
            Object valor,
            CellStyle estiloFecha
    ) {

        if (valor instanceof java.sql.Date fecha) {
            celda.setCellValue(fecha.toLocalDate());
            celda.setCellStyle(estiloFecha);
            return;
        }

        if (valor instanceof LocalDate fecha) {
            celda.setCellValue(fecha);
            celda.setCellStyle(estiloFecha);
            return;
        }

        celda.setCellValue(valor.toString());
    }

    private void escribirFechaHora(
            Cell celda,
            Object valor,
            CellStyle estiloFechaHora
    ) {

        if (valor instanceof java.sql.Timestamp fechaHora) {
            celda.setCellValue(fechaHora.toLocalDateTime());
            celda.setCellStyle(estiloFechaHora);
            return;
        }

        if (valor instanceof LocalDateTime fechaHora) {
            celda.setCellValue(fechaHora);
            celda.setCellStyle(estiloFechaHora);
            return;
        }

        celda.setCellValue(valor.toString());
    }

    private void escribirHora(
            Cell celda,
            Object valor,
            CellStyle estiloHora
    ) {

        if (valor instanceof java.sql.Time hora) {
            LocalDateTime fechaHora = LocalDateTime.of(
                    LocalDate.of(1899, 12, 31),
                    hora.toLocalTime()
            );

            celda.setCellValue(fechaHora);
            celda.setCellStyle(estiloHora);
            return;
        }

        if (valor instanceof LocalTime hora) {
            LocalDateTime fechaHora = LocalDateTime.of(
                    LocalDate.of(1899, 12, 31),
                    hora
            );

            celda.setCellValue(fechaHora);
            celda.setCellStyle(estiloHora);
            return;
        }

        celda.setCellValue(valor.toString());
    }

    private void escribirBooleano(Cell celda, Object valor) {

        if (valor instanceof Boolean booleano) {
            celda.setCellValue(booleano);
            return;
        }

        celda.setCellValue(valor.toString());
    }

    private void escribirValorPorDefecto(Cell celda, Object valor) {

        if (valor instanceof Number numero) {
            celda.setCellValue(numero.doubleValue());
            return;
        }

        if (valor instanceof Boolean booleano) {
            celda.setCellValue(booleano);
            return;
        }

        if (valor instanceof java.sql.Date fecha) {
            celda.setCellValue(fecha.toLocalDate());
            return;
        }

        if (valor instanceof java.sql.Timestamp fechaHora) {
            celda.setCellValue(fechaHora.toLocalDateTime());
            return;
        }

        celda.setCellValue(valor.toString());
    }

    private CellStyle crearEstiloCabecera(Workbook workbook) {

        Font fuente = workbook.createFont();
        fuente.setBold(true);

        CellStyle estilo = workbook.createCellStyle();
        estilo.setFont(fuente);

        return estilo;
    }

    private CellStyle crearEstiloFecha(Workbook workbook) {

        CellStyle estilo = workbook.createCellStyle();
        CreationHelper helper = workbook.getCreationHelper();
        DataFormat formato = helper.createDataFormat();

        estilo.setDataFormat(formato.getFormat("yyyy-mm-dd"));

        return estilo;
    }

    private CellStyle crearEstiloFechaHora(Workbook workbook) {

        CellStyle estilo = workbook.createCellStyle();
        CreationHelper helper = workbook.getCreationHelper();
        DataFormat formato = helper.createDataFormat();

        estilo.setDataFormat(formato.getFormat("yyyy-mm-dd hh:mm:ss"));

        return estilo;
    }

    private CellStyle crearEstiloHora(Workbook workbook) {

        CellStyle estilo = workbook.createCellStyle();
        CreationHelper helper = workbook.getCreationHelper();
        DataFormat formato = helper.createDataFormat();

        estilo.setDataFormat(formato.getFormat("hh:mm:ss"));

        return estilo;
    }
}