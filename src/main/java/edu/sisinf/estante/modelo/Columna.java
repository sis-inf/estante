package edu.sisinf.estante.modelo;

public class Columna {
    private String nombre;
    private String tipoSql;
    private boolean nullable;
    private boolean esPrimaryKey;

    //Constructor sin argumentos requerido por Jackson
    public Columna() {}

    //Constructor con todos los campos
    public Columna(String nombre, String tipoSql, boolean nullable, boolean esPrimaryKey) {
        this.nombre = nombre;
        this.tipoSql = tipoSql;
        this.nullable = nullable;
        this.esPrimaryKey = esPrimaryKey;
    }

    //Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipoSql() { return tipoSql; }
    public void setTipoSql(String tipoSql) { this.tipoSql = tipoSql; }

    public boolean isNullable() { return nullable; }
    public void setNullable(boolean nullable) { this.nullable = nullable; }

    public boolean isEsPrimaryKey() { return esPrimaryKey; }
    public void setEsPrimaryKey(boolean esPrimaryKey) { this.esPrimaryKey = esPrimaryKey; }
}
