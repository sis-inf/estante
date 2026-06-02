package edu.sisinf.estante.controller;

import edu.sisinf.estante.modelo.ResultadoQuery;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.function.Consumer;

/**
 * Controller del panel de resultado de queries.
 */
public class PanelResultadoQueryController {

    @FXML
    private TableView<List<Object>> tablaResultado;

    @FXML
    private Label labelEstado;

    @FXML
    private Button botonExportar;

    private ResultadoQuery resultadoActual;

    private Consumer<ResultadoQuery> onExportar;

    @FXML
    public void initialize() {
        botonExportar.setOnAction(event -> {
            if (onExportar != null && resultadoActual != null) {
                onExportar.accept(resultadoActual);
            }
        });
    }

    public void mostrar(ResultadoQuery resultado) {
        this.resultadoActual = resultado;

        tablaResultado.getColumns().clear();
        tablaResultado.getItems().clear();

        switch (resultado.getTipo()) {

            case LECTURA:
                List<String> columnas = resultado.getColumnas();

                for (int i = 0; i < columnas.size(); i++) {
                    final int indice = i;

                    TableColumn<List<Object>, Object> columna =
                            new TableColumn<>(columnas.get(i));

                    columna.setCellValueFactory(data ->
                            new ReadOnlyObjectWrapper<>(
                                    data.getValue().get(indice)
                            ));

                    tablaResultado.getColumns().add(columna);
                }

                tablaResultado.setItems(
                        FXCollections.observableArrayList(resultado.getFilas())
                );

                labelEstado.setText(
                        resultado.getFilas().size()
                                + " filas (" +
                                resultado.getTiempoMs() +
                                " ms)"
                );

                botonExportar.setDisable(false);
                break;

            case ESCRITURA:
                labelEstado.setText(
                        resultado.getFilasAfectadas()
                                + " filas afectadas (" +
                                resultado.getTiempoMs() +
                                " ms)"
                );

                botonExportar.setDisable(true);
                break;

            case ERROR:
                labelEstado.setText(
                        "Error: " + resultado.getMensaje()
                );

                botonExportar.setDisable(true);
                break;
        }
    }

    public void setOnExportar(Consumer<ResultadoQuery> callback) {
        this.onExportar = callback;
    }

    public TableView<List<Object>> getTablaResultado() {
        return tablaResultado;
    }

    public Label getLabelEstado() {
        return labelEstado;
    }

    public Button getBotonExportar() {
        return botonExportar;
    }
}