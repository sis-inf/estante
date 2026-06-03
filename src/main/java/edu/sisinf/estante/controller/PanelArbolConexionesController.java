package edu.sisinf.estante.controller;

import edu.sisinf.estante.modelo.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Controller del panel izquierdo de Estante.
 *
 * <p>Gestiona la estructura visual del árbol de conexiones guardadas
 * e implementa criterios de filtrado dinámico en tiempo real de forma case-insensitive.</p>
 *
 * <p>Archivos relacionados:</p>
 * <ul>
 * <li>Vista: {@code src/main/resources/fxml/PanelArbolConexiones.fxml}</li>
 * </ul>
 */
public class PanelArbolConexionesController {

    // Campos FXML

    @FXML private Button btnNuevaConexion;
    @FXML private Button btnRefrescar;
    @FXML private Button btnEliminar;
    @FXML private TextField txtBusqueda;
    @FXML private TreeView<Object> arbol;

    // Estado interno

    private Consumer<Conexion> onConexionDoubleClick = null;

    /**
     * Resguardo local de la lista completa para poder restaurar el estado del árbol
     * cuando el campo de búsqueda sea modificado o limpiado.
     */
    private final List<Conexion> conexionesOriginales = new ArrayList<>();

    // Inicialización

    /**
     * Inicializa el árbol con un nodo raíz fijo "Conexiones", registra
     * el manejador de doble clic y configura el listener dinámico de búsqueda.
     */
    @FXML
    public void initialize() {
        TreeItem<Object> raiz = new TreeItem<>("Conexiones");
        raiz.setExpanded(true);
        arbol.setRoot(raiz);

        arbol.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TreeItem<Object> seleccionado = arbol.getSelectionModel().getSelectedItem();
                if (seleccionado != null && seleccionado.getValue() instanceof Conexion conexion) {
                    if (onConexionDoubleClick != null) {
                        onConexionDoubleClick.accept(conexion);
                    }
                }
            }
        });

        txtBusqueda.textProperty().addListener((javafx.beans.value.ObservableValue<? extends String> obs, String viejo, String nuevo) -> {
            filtrarArbol(nuevo);
        });
    }

    // API pública

    /**
     * Reemplaza los hijos del nodo raíz con los ítems de la lista dada y preserva el estado original.
     *
     * @param lista Lista de conexiones a mostrar; no debe ser {@code null}.
     */
    public void cargarConexiones(List<Conexion> lista) {
        if (lista == null) {
            return;
        }
        
        // Mantener el respaldo actualizado con las conexiones reales de la app
        conexionesOriginales.clear();
        conexionesOriginales.addAll(lista);

        // Renderizar aplicando el filtro existente si es que el usuario ya escribió algo
        filtrarArbol(txtBusqueda.getText());
    }

    /**
     * Procesa el filtrado del árbol basándose en la cadena ingresada.
     * Si está vacío, restaura todas las conexiones de forma completa.
     */
    private void filtrarArbol(String filtro) {
        TreeItem<Object> raiz = arbol.getRoot();
        raiz.getChildren().clear();

        if (filtro == null || filtro.isBlank()) {
            for (Conexion conexion : conexionesOriginales) {
                TreeItem<Object> itemConexion = new TreeItem<>(conexion);
                itemConexion.getChildren().add(new TreeItem<>("(cargando...)"));
                raiz.getChildren().add(itemConexion);
            }
        } else {
            String termino = filtro.toLowerCase().trim();

            for (Conexion conexion : conexionesOriginales) {
                String nombreConexion = (conexion != null) ? conexion.toString().toLowerCase() : "";
                
                if (nombreConexion.contains(termino)) {
                    TreeItem<Object> itemConexion = new TreeItem<>(conexion);
                    itemConexion.getChildren().add(new TreeItem<>("(cargando...)"));
                    raiz.getChildren().add(itemConexion);
                }
            }
        }
    }

    public void setOnConexionDoubleClick(Consumer<Conexion> callback) {
        this.onConexionDoubleClick = callback;
    }

    public TreeItem<Object> getNodoSeleccionado() {
        return arbol.getSelectionModel().getSelectedItem();
    }

    public Button getBotonNuevaConexion() {
        return btnNuevaConexion;
    }

    public Button getBotonRefrescar() {
        return btnRefrescar;
    }

    public Button getBotonEliminar() {
        return btnEliminar;
    }
}