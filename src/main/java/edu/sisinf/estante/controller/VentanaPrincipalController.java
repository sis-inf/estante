package edu.sisinf.estante.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

/**
 * Controller principal de la ventana base de Estante.
 */
public class VentanaPrincipalController {

    @FXML
    private MenuItem menuNuevaConexion;

    @FXML
    private MenuItem menuSalir;

    @FXML
    private MenuItem menuAcercaDe;

    @FXML
    private StackPane contenedorArbol;

    @FXML
    private StackPane contenedorEditor;

    @FXML
    private StackPane contenedorResultado;

    @FXML
    private StackPane contenedorBarraEstado;

    @FXML
    public void initialize() {
        menuSalir.setOnAction(event -> Platform.exit());
    }

    public MenuItem getMenuNuevaConexion() {
        return menuNuevaConexion;
    }

    public MenuItem getMenuSalir() {
        return menuSalir;
    }

    public MenuItem getMenuAcercaDe() {
        return menuAcercaDe;
    }

    public StackPane getContenedorArbol() {
        return contenedorArbol;
    }

    public StackPane getContenedorEditor() {
        return contenedorEditor;
    }

    public StackPane getContenedorResultado() {
        return contenedorResultado;
    }

    public StackPane getContenedorBarraEstado() {
        return contenedorBarraEstado;
    }
}