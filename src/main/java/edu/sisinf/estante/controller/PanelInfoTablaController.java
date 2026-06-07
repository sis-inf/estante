// ============================================================
// ARCHIVO: PanelInfoTablaController.java
// RUTA: src/main/java/edu/sisinf/estante/controller/PanelInfoTablaController.java
// ============================================================

package edu.sisinf.estante.controller;

import edu.sisinf.estante.modelo.Columna;
import edu.sisinf.estante.modelo.Tabla;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Controller del panel de información de tabla.
 *
 * <p>Muestra las columnas de la tabla seleccionada en el árbol de conexiones
 * y expone botones para generar sentencias SQL (SELECT, INSERT, UPDATE, DELETE)
 * que se envían al editor SQL mediante un callback.</p>
 *
 * <p>Este controller es deliberadamente "tonto": no ejecuta queries ni
 * interactúa con la base de datos directamente.</p>
 */
public class PanelInfoTablaController {

    // --------------------------------------------------------
    // FXML fields
    // --------------------------------------------------------

    @FXML private Label                      labelNombreTabla;
    @FXML private Button                     btnGenerarSelect;
    @FXML private Button                     btnGenerarInsert;
    @FXML private Button                     btnGenerarUpdate;
    @FXML private Button                     btnGenerarDelete;
    @FXML private TableView<Columna>         tablaColumnas;
    @FXML private TableColumn<Columna, String>  colNombre;
    @FXML private TableColumn<Columna, String>  colTipo;
    @FXML private TableColumn<Columna, String>  colNullable;
    @FXML private TableColumn<Columna, String> colPK;

    // --------------------------------------------------------
    // State
    // --------------------------------------------------------

    /** Tabla actualmente mostrada en el panel. */
    private Tabla tablaActual;

    /**
     * Callback invocado cuando el usuario hace clic en un botón de generación.
     * Recibe el SQL generado como {@code String}.
     */
    private Consumer<String> onSqlGenerado;

    // --------------------------------------------------------
    // Initialization
    // --------------------------------------------------------

    /**
     * Configura las columnas del TableView y conecta los botones de generación.
     */
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colTipo.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().getTipoSql()));
        colNullable.setCellValueFactory(
                cell -> new SimpleStringProperty(cell.getValue().isNullable() ? "YES" : "NO"));
        colPK.setCellValueFactory(
        cell -> new SimpleStringProperty(
                cell.getValue().isEsPrimaryKey() ? "YES" : "NO"));

        btnGenerarSelect.setOnAction(e -> generarYEnviar(this::buildSelect));
        btnGenerarInsert.setOnAction(e -> generarYEnviar(this::buildInsert));
        btnGenerarUpdate.setOnAction(e -> generarYEnviar(this::buildUpdate));
        btnGenerarDelete.setOnAction(e -> generarYEnviar(this::buildDelete));
    }

    // --------------------------------------------------------
    // Public API
    // --------------------------------------------------------

    /**
     * Carga la información de la tabla dada en el panel.
     *
     * @param tabla tabla a mostrar; no debe ser {@code null}
     */
    public void cargarTabla(Tabla tabla) {
        this.tablaActual = tabla;
        labelNombreTabla.setText(tabla.getNombre());
        tablaColumnas.getItems().setAll(tabla.getColumnas());
    }

    /**
     * Registra el callback que recibe el SQL generado cuando el usuario
     * hace clic en uno de los botones de generación.
     *
     * @param callback {@code Consumer<String>} que recibe el SQL; puede ser
     *                 {@code null} para desregistrar
     */
    public void setOnSqlGenerado(Consumer<String> callback) {
        this.onSqlGenerado = callback;
    }

    /** @return botón SELECT */
    public Button getBotonGenerarSelect() { return btnGenerarSelect; }

    /** @return botón INSERT */
    public Button getBotonGenerarInsert() { return btnGenerarInsert; }

    /** @return botón UPDATE */
    public Button getBotonGenerarUpdate() { return btnGenerarUpdate; }

    /** @return botón DELETE */
    public Button getBotonGenerarDelete() { return btnGenerarDelete; }

    // --------------------------------------------------------
    // Private helpers
    // --------------------------------------------------------

    /**
     * Genera el SQL usando el builder dado y lo envía al callback si está registrado.
     *
     * @param builder función que genera el SQL a partir de la tabla actual
     */
    private void generarYEnviar(java.util.function.Supplier<String> builder) {
        if (tablaActual == null || onSqlGenerado == null) return;
        onSqlGenerado.accept(builder.get());
    }

    /**
     * Genera un SELECT con todas las columnas de la tabla actual.
     *
     * @return sentencia SELECT sintácticamente correcta
     */
    private String buildSelect() {
        String columns = tablaActual.getColumnas().stream()
                .map(Columna::getNombre)
                .collect(Collectors.joining(", "));
        return "SELECT " + columns + "\nFROM " + tablaActual.getNombre() + ";";
    }

    /**
     * Genera un INSERT con todas las columnas de la tabla actual.
     *
     * @return sentencia INSERT sintácticamente correcta
     */
    private String buildInsert() {
        List<Columna> columnas = tablaActual.getColumnas();
        String columns = columnas.stream()
                .map(Columna::getNombre)
                .collect(Collectors.joining(", "));
        String placeholders = columnas.stream()
                .map(c -> "?")
                .collect(Collectors.joining(", "));
        return "INSERT INTO " + tablaActual.getNombre()
                + " (" + columns + ")\nVALUES (" + placeholders + ");";
    }

    /**
     * Genera un UPDATE con todas las columnas de la tabla actual.
     * Usa un WHERE con la primera columna como condición de ejemplo.
     *
     * @return sentencia UPDATE sintácticamente correcta
     */
    private String buildUpdate() {
        List<Columna> columnas = tablaActual.getColumnas();
        String sets = columnas.stream()
                .map(c -> c.getNombre() + " = ?")
                .collect(Collectors.joining(",\n    "));
        String whereColumn = columnas.isEmpty() ? "id" : columnas.get(0).getNombre();
        return "UPDATE " + tablaActual.getNombre()
                + "\nSET " + sets
                + "\nWHERE " + whereColumn + " = ?;";
    }

    /**
     * Genera un DELETE con un WHERE sobre la primera columna como condición de ejemplo.
     *
     * @return sentencia DELETE sintácticamente correcta
     */
    private String buildDelete() {
        String whereColumn = tablaActual.getColumnas().isEmpty()
                ? "id"
                : tablaActual.getColumnas().get(0).getNombre();
        return "DELETE FROM " + tablaActual.getNombre()
                + "\nWHERE " + whereColumn + " = ?;";
    }
}