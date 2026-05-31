package edu.sisinf.estante.modelo;

/**
 * Representa los datos de conexion necesarios para establecer
 * una sesion de base de datos.
 */
public class Conexion {

    private String id;
    private String nombre;
    private String host;
    private Integer puerto;
    private String baseDatos;
    private String usuario;
    private String contrasena;
    private TipoMotor tipoMotor;
    private boolean usarSSL = false;

    public Conexion() {
        this.puerto = 3306;
    }

    public Conexion(String nombre, String host, Integer puerto,
                    String baseDatos, String usuario, String contrasena) {
        this.nombre    = nombre;
        this.host      = host;
        this.puerto    = puerto;
        this.baseDatos = baseDatos;
        this.usuario   = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getId()                  { return id; }
    public void setId(String id)           { this.id = id; }

    public String getNombre()              { return nombre; }
    public void setNombre(String nombre)   { this.nombre = nombre; }

    public String getHost()                { return host; }
    public void setHost(String host)       { this.host = host; }

    public Integer getPuerto()             { return puerto; }
    public void setPuerto(Integer puerto)  { this.puerto = puerto; }

    public String getBaseDatos()           { return baseDatos; }
    public void setBaseDatos(String bd)    { this.baseDatos = bd; }

    public String getUsuario()             { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena()               { return contrasena; }
    public void setContrasena(String contrasena){ this.contrasena = contrasena; }

    public TipoMotor getTipoMotor()              { return tipoMotor; }
    public void setTipoMotor(TipoMotor motor)    { this.tipoMotor = motor; }

    /**
     * Indica si la conexión debe usar SSL/TLS.
     * @return true si SSL está habilitado, false por defecto.
     */
    public boolean isUsarSSL()             { return usarSSL; }
    public void setUsarSSL(boolean ssl)    { this.usarSSL = ssl; }

    @Override
    public String toString() {
        return nombre + "@" + host + ":" + puerto + "/" + baseDatos;
    }
}