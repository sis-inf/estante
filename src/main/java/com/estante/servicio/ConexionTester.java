package com.estante.servicio;

import com.estante.modelo.ResultadoPrueba;
import com.estante.modelo.Conexion; // Si el compilador no la encuentra globalmente, la lee del archivo anterior
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionTester {

    public ResultadoPrueba probar(Conexion conexion) {
        long inicio = System.currentTimeMillis();
        
        try (Connection conn = DriverManager.getConnection(
                conexion.getUrl(), 
                conexion.getUsuario(), 
                conexion.getContrasena());
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