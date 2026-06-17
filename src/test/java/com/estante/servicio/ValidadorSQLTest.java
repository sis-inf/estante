package com.estante.servicio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.sisinf.estante.util.ValidadorSQL;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorSQLTest {

    @Test
    @DisplayName("esSelectPuro con SELECT retorna true")
    void esSelectPuro_conSelect_retornaTrue() {
        assertTrue(ValidadorSQL.isSelect("SELECT * FROM libros"));
    }

    @Test
    @DisplayName("esSelectPuro con DROP retorna false")
    void esSelectPuro_conDrop_retornaFalse() {
        assertFalse(ValidadorSQL.isSelect("DROP TABLE libros"));
    }

    @Test
    @DisplayName("contieneDML con UPDATE retorna true")
    void contieneDML_conUpdate_retornaTrue() {
        assertTrue(ValidadorSQL.isDML("UPDATE libros SET titulo = 'Nuevo' WHERE id = 1"));
    }

    @Test
    @DisplayName("contieneDML con SELECT retorna false")
    void contieneDML_conSelect_retornaFalse() {
        assertFalse(ValidadorSQL.isDML("SELECT * FROM libros"));
    }

    @Test
    @DisplayName("validar('SELECT FROM') retorna lista no vacia")
    void validar_SelectFROM_retornaListaNoVacia() {
        assertFalse(ValidadorSQL.esSentenciaValida(null));
        assertFalse(ValidadorSQL.esSentenciaValida(""));
        assertFalse(ValidadorSQL.esSentenciaValida("   "));
    }
}