package edu.sisinf.estante.controller;

import edu.sisinf.estante.util.SqlValidator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

/**
 * Controller del panel editor SQL.
 * Permite al usuario escribir y ejecutar consultas SQL.
 * El editor no ejecuta queries directamente: dispara un callback
 * registrado externamente con el texto actual del editor.
 */
public class PanelEditorSQLController {

    @FXML private Button btnEjecutar;
    @FXML private Button btnLimpiar;
    @FXML private Label labelConexionActiva;
    @FXML private TextArea areaSQL;

    private Consumer<String> onEjecutar;

    /**
     * Inicializa el controller conectando los botones y el atajo de teclado.
     */
    @FXML
    public void initialize() {

        // Botón limpiar
        btnLimpiar.setOnAction(e -> areaSQL.clear());

        // Botón ejecutar
        btnEjecutar.setOnAction(e -> disparar());

        // Ctrl+Enter dispara el callback sin agregar salto de línea
        areaSQL.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER && event.isControlDown()) {
                event.consume();
                disparar();
            }
        });
    }

    /**
     * Registra el callback que se invoca al ejecutar una query.
     *
     * @param callback {@code Consumer<String>} que recibe el texto del editor.
     */
    public void setOnEjecutar(Consumer<String> callback) {
        this.onEjecutar = callback;
    }

    /**
     * Actualiza el label de conexión activa.
     *
     * @param descripcion Descripción de la conexión activa.
     *                    Si es {@code null}, muestra "Sin conexión".
     */
    public void setConexionActiva(String descripcion) {
        labelConexionActiva.setText(descripcion != null ? descripcion : "Sin conexión");
    }

    /**
     * Devuelve el texto actual del editor SQL.
     *
     * @return Texto escrito en el {@code TextArea}.
     */
    public String getTextoSQL() {
        return areaSQL.getText();
    }

    /**
     * Dispara el callback si el texto no está vacío.
     */
    private void disparar() {

    String texto = areaSQL.getText();

    if (texto.isBlank() || onEjecutar == null) {
        return;
    }

    if (SqlValidator.esDestructiva(texto)) {

        boolean confirmar =
                DialogoConfirmacionDML.confirmar(texto);

        if (!confirmar) {
            return;
        }
    }

    onEjecutar.accept(texto);
}

}