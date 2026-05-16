// ============================================================
// ARCHIVO: Conexion.java
// RUTA: src/main/java/edu/sisinf/estante/modelo/Conexion.java
// ============================================================
package edu.sisinf.estante.modelo;

/**
 * Representa los datos de conexion necesarios para establecer una sesion de base de datos MySQL.
 * Se trata de un objeto Java simple (POJO) sin dependencias externas.
 */
public class Conexion {
    private String id;            
    private String nombre;        
    private String host;          
    private int puerto;       
    private String baseDatos;     
    private String usuario;      
    private String contrasena;      

    /** Constructor sin argumentos. Establece el puerto predeterminado en 3306. */

    public Conexion() {
        this.puerto = 3306;
    }

    /**
    * Constructor completo (excluye el ID, que se asigna externamente).
    *
    * @param nombre Etiqueta legible para esta conexion
    * @param host Nombre de host o direccion IP del servidor
    * @param puerto Puerto TCP (normalmente 3306)
    * @param usuario Nombre de usuario de MySQL
    * @param contrasena Contraseña de MySQL
    * @param baseDatos Nombre de la base de datos de destino
    */
    public Conexion(String nombre,String host, int puerto,
                    String baseDatos,String usuario, String contrasena) {
        this.nombre = nombre; 
        this.host = host;
        this.puerto = puerto;
        this.baseDatos = baseDatos;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPuerto() { return puerto; }
    public void setPuerto(int puerto) { this.puerto = puerto; }

    public String getBaseDatos() { return baseDatos; }
    public void setBaseDatos(String baseDatos) { this.baseDatos = baseDatos; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // --------------------------------------------------------
    // Sobrescrituras de objetos
    // --------------------------------------------------------
    /**

    * Devuelve una representacion segura en cadena de esta conexion.
    * La contraseña se omite intencionalmente para evitar su exposicion accidental en los registros.
    *
    * @return cadena formateada: nombre@host:puerto/baseDatos
    */
    @Override
    public String toString() {
        return nombre + "@" + host + ":" + puerto + "/" + baseDatos;
    }
}