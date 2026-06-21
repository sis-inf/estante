package com.estante.servicio;

import com.estante.modelo.ResultadoPrueba;
import edu.sisinf.estante.modelo.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionTester {

    /**
     * Prueba una conexión construyendo la URL JDBC con los datos reales de la clase Conexion.
     */
    public ResultadoPrueba probar(Conexion conexion) {
        long inicio = System.currentTimeMillis();
        
        // Construimos el string JDBC: jdbc:mysql://host:puerto/basedatos
        String urlJdbc = String.format("jdbc:mysql://%s:%d/%s", 
                conexion.getHost(), 
                conexion.getPuerto(), 
                conexion.getBasedatos());
        
        try (Connection conn = DriverManager.getConnection(
                urlJdbc, 
                conexion.getUsuario(), 
                conexion.getPassword());
             Statement stmt = conn.createStatement()) {
            
            stmt.executeQuery("SELECT 1");
            
            long tiempoMs = System.currentTimeMillis() - inicio;
            return new ResultadoPrueba(true, tiempoMs, null);
            
        } catch (Exception e) {
            long tiempoMs = System.currentTimeMillis() - inicio;
            String mensajeError = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
            return new ResultadoPrueba(false, tiempoMs, mensajeError);
        }
    }
}