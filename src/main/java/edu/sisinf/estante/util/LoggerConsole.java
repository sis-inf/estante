package edu.sisinf.estante.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger de consola con niveles básicos.
 * Implementa el patrón Singleton.
 */
public class LoggerConsole {

    private static LoggerConsole instancia;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss");

    private LoggerConsole() {
    }

    public static LoggerConsole getInstancia() {
        if (instancia == null) {
            instancia = new LoggerConsole();
        }
        return instancia;
    }

    public void info(String mensaje) {
        System.out.println(ahora() + " [INFO] " + mensaje);
    }

    public void warn(String mensaje) {
        System.out.println(ahora() + " [WARN] " + mensaje);
    }

    public void error(String mensaje) {
        System.err.println(ahora() + " [ERROR] " + mensaje);
    }

    private String ahora() {
        return "[" + LocalTime.now().format(FORMATO) + "]";
    }
}