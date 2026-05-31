package edu.sisinf.estante.controller;
import edu.sisinf.estante.modelo.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import java.util.List;
import java.util.function.Consumer;

/**
 * Controller del panel izquierdo de Estante.
 *
 * <p>Gestiona la estructura visual del árbol de conexiones guardadas.
 * No abre conexiones JDBC ni carga esquemas directamente: expone hooks
 * para que la integración reaccione a las interacciones del usuario.</p>
 *
 * <p>Archivos relacionados:</p>
 * <ul>
 *   <li>Vista: {@code src/main/resources/fxml/PanelArbolConexiones.fxml}</li>
 * </ul>
 */
public class PanelArbolConexionesController {

    // Campos FXML

    @FXML private Button btnNuevaConexion;
    @FXML private Button btnRefrescar;
    @FXML private Button btnEliminar;
    @FXML private TreeView<Object> arbol;

    // Estado interno

    /**
     * Callback invocado cuando el usuario hace doble clic sobre una
     * {@link Conexion} en el árbol. Inicializado en {@code null};
     * se reemplaza mediante {@link #setOnConexionDoubleClick(Consumer)}.
     */
    private Consumer<Conexion> onConexionDoubleClick = null;

    // Inicialización

    /**
     * Inicializa el árbol con un nodo raíz fijo "Conexiones" y registra
     * el manejador de doble clic.
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
    }

    // API pública

    /**
     * Reemplaza los hijos del nodo raíz con los ítems de la lista dada.
     *
     * <p>Cada {@link Conexion} se agrega como {@link TreeItem} hijo del root.
     * Se añade un hijo placeholder con texto {@code "(cargando...)"} para que
     * el árbol muestre la flecha de expansión antes de que se carguen los
     * esquemas reales.</p>
     *
     * @param lista Lista de conexiones a mostrar; no debe ser {@code null}.
     */
    public void cargarConexiones(List<Conexion> lista) {
        TreeItem<Object> raiz = arbol.getRoot();
        raiz.getChildren().clear();

        for (Conexion conexion : lista) {
            TreeItem<Object> itemConexion = new TreeItem<>(conexion);
            itemConexion.getChildren().add(new TreeItem<>("(cargando...)"));
            raiz.getChildren().add(itemConexion);
        }
    }

    /**
     * Registra el callback invocado cuando el usuario hace doble clic
     * sobre una conexión del árbol.
     *
     * <p>La integración usará este callback para abrir la conexión y
     * cargar sus esquemas desde {@code ExploradorEsquemas}.</p>
     *
     * @param callback {@link Consumer} que recibe la {@link Conexion}
     *                 seleccionada; puede ser {@code null} para desregistrar.
     */
    public void setOnConexionDoubleClick(Consumer<Conexion> callback) {
        this.onConexionDoubleClick = callback;
    }

    /**
     * Devuelve el ítem actualmente seleccionado en el árbol.
     *
     * <p>Útil cuando el usuario pulsa "Eliminar" o "Refrescar" para saber
     * sobre qué nodo actuar.</p>
     *
     * @return El {@link TreeItem} seleccionado, o {@code null} si no hay
     *         ninguno.
     */
    public TreeItem<Object> getNodoSeleccionado() {
        return arbol.getSelectionModel().getSelectedItem();
    }

    // Getters de botones (para que la integración conecte sus handlers)

    /**
     * Devuelve el botón "Nueva conexión" (+).
     *
     * @return botón de nueva conexión.
     */
    public Button getBotonNuevaConexion() {
        return btnNuevaConexion;
    }

    /**
     * Devuelve el botón "Refrescar" (↺).
     *
     * @return botón de refrescar.
     */
    public Button getBotonRefrescar() {
        return btnRefrescar;
    }

    /**
     * Devuelve el botón "Eliminar" (−).
     *
     * @return botón de eliminar.
     */
    public Button getBotonEliminar() {
        return btnEliminar;
    }
}