package edu.sisinf.estante.servicio;

import edu.sisinf.estante.core.ErrorPersistencia;
import edu.sisinf.estante.modelo.ResultadoQuery;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Servicio para exportar resultados de consultas a archivos Excel (.xlsx).
 *
 * Usa Apache POI para generar el archivo. La primera fila contiene
 * los nombres de columna en negrita. Detecta tipos numéricos para
 * formatearlos correctamente.
 */
public class ExportadorExcel {

    /**
     * Exporta un resultado de consulta a un archivo .xlsx.
     *
     * Si el archivo ya existe, se sobreescribe.
     *
     * @param resultado resultado de la consulta
     * @param archivo   archivo destino
     */
    public void exportar(ResultadoQuery resultado, File archivo) {

        if (resultado.getTipo() != ResultadoQuery.Tipo.LECTURA) {
            throw new ErrorPersistencia(
                    "Solo se pueden exportar resultados de lectura"
            );
        }

        List<String> columnas = resultado.getColumnas();
        List<List<Object>> filas = resultado.getFilas();

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream salida = new FileOutputStream(archivo)) {

            Sheet hoja = workbook.createSheet("Resultado");

            // Estilo para cabeceras en negrita
            CellStyle estiloCabecera = workbook.createCellStyle();
            Font fuenteNegrita = workbook.createFont();
            fuenteNegrita.setBold(true);
            estiloCabecera.setFont(fuenteNegrita);

            // Estilo para números
            CellStyle estiloNumero = workbook.createCellStyle();
            DataFormat formato = workbook.createDataFormat();
            estiloNumero.setDataFormat(formato.getFormat("#,##0.##"));

            // Fila de cabeceras
            Row filaCabecera = hoja.createRow(0);
            for (int c = 0; c < columnas.size(); c++) {
                Cell celda = filaCabecera.createCell(c);
                celda.setCellValue(columnas.get(c));
                celda.setCellStyle(estiloCabecera);
            }

            // Filas de datos
            for (int f = 0; f < filas.size(); f++) {
                Row fila = hoja.createRow(f + 1);
                List<Object> datos = filas.get(f);

                for (int c = 0; c < datos.size(); c++) {
                    Cell celda = fila.createCell(c);
                    Object valor = datos.get(c);

                    if (valor == null) {
                        celda.setBlank();
                    } else if (valor instanceof Number numero) {
                        celda.setCellValue(numero.doubleValue());
                        celda.setCellStyle(estiloNumero);
                    } else if (valor instanceof Boolean booleano) {
                        celda.setCellValue(booleano);
                    } else {
                        celda.setCellValue(valor.toString());
                    }
                }
            }

            // Ajustar ancho de columnas automáticamente
            for (int c = 0; c < columnas.size(); c++) {
                hoja.autoSizeColumn(c);
            }

            workbook.write(salida);

        } catch (IOException e) {
            throw new ErrorPersistencia(
                    "Error al exportar archivo Excel",
                    e
            );
        }
    }
}