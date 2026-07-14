package com.estante.util;

public class StringUtils {

    public static String truncar(String texto, int maxLength) {
        if (texto == null) {
            return null;
        }

        if (texto.length() <= maxLength) {
            return texto;
        }

        return texto.substring(0, maxLength - 3) + "...";
    }

    public static String enmascarar(String valor) {
        if (valor == null) {
            return null;
        }

        if (valor.length() <= 4) {
            return "*****";
        }

        return "*".repeat(valor.length() - 4) + valor.substring(valor.length() - 4);
    }

    public static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        String[] palabras = texto.split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(
                    palabra.substring(0, 1).toUpperCase()
                ).append(
                    palabra.substring(1).toLowerCase()
                );
            }
            resultado.append(" ");
        }

        return resultado.toString().trim();
    }

    public static boolean esVacioONulo(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
