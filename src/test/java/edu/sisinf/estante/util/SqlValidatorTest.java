package edu.sisinf.estante.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de SqlValidator.
 * Cubren clasificación de tipo, lectura, DML, DDL y consultas destructivas.
 */
class SqlValidatorTest {

    // -------------------------------------------------------------------------
    // tipo() — reconocimiento de SELECT
    // -------------------------------------------------------------------------

    @Test
    void tipo_selectEnMinusculas_retornaSelect() {
        // Verifica que "select" en minúsculas se clasifica como SELECT
        assertEquals(SqlValidator.TipoQuery.SELECT, SqlValidator.tipo("select * from libros"));
    }

    @Test
    void tipo_selectEnMayusculas_retornaSelect() {
        // Verifica que "SELECT" en mayúsculas se clasifica como SELECT
        assertEquals(SqlValidator.TipoQuery.SELECT, SqlValidator.tipo("SELECT * FROM libros"));
    }

    @Test
    void tipo_selectConEspaciosAlInicio_retornaSelect() {
        // Verifica que los espacios iniciales no impiden la clasificación
        assertEquals(SqlValidator.TipoQuery.SELECT, SqlValidator.tipo("   SELECT * FROM t"));
    }

    // -------------------------------------------------------------------------
    // tipo() — reconocimiento de cada tipo DML/DDL
    // -------------------------------------------------------------------------

    @Test
    void tipo_insert_retornaInsert() {
        // Verifica que INSERT se identifica correctamente
        assertEquals(SqlValidator.TipoQuery.INSERT, SqlValidator.tipo("INSERT INTO libros (id) VALUES (1)"));
    }

    @Test
    void tipo_update_retornaUpdate() {
        // Verifica que UPDATE se identifica correctamente
        assertEquals(SqlValidator.TipoQuery.UPDATE, SqlValidator.tipo("UPDATE libros SET titulo='a' WHERE id=1"));
    }

    @Test
    void tipo_delete_retornaDelete() {
        // Verifica que DELETE se identifica correctamente
        assertEquals(SqlValidator.TipoQuery.DELETE, SqlValidator.tipo("DELETE FROM libros WHERE id=1"));
    }

    @Test
    void tipo_create_retornaCreate() {
        // Verifica que CREATE se identifica correctamente
        assertEquals(SqlValidator.TipoQuery.CREATE, SqlValidator.tipo("CREATE TABLE libros (id INT)"));
    }

    @Test
    void tipo_drop_retornaDrop() {
        // Verifica que DROP se identifica correctamente
        assertEquals(SqlValidator.TipoQuery.DROP, SqlValidator.tipo("DROP TABLE libros"));
    }

    @Test
    void tipo_alter_retornaAlter() {
        // Verifica que ALTER se identifica correctamente
        assertEquals(SqlValidator.TipoQuery.ALTER, SqlValidator.tipo("ALTER TABLE libros ADD COLUMN autor TEXT"));
    }

    // -------------------------------------------------------------------------
    // tipo() — casos DESCONOCIDO
    // -------------------------------------------------------------------------

    @Test
    void tipo_null_retornaDesconocido() {
        // null no tiene tipo reconocible
        assertEquals(SqlValidator.TipoQuery.DESCONOCIDO, SqlValidator.tipo(null));
    }

    @Test
    void tipo_cadenaVacia_retornaDesconocido() {
        // Cadena vacía no tiene tipo reconocible
        assertEquals(SqlValidator.TipoQuery.DESCONOCIDO, SqlValidator.tipo(""));
    }

    @Test
    void tipo_soloEspacios_retornaDesconocido() {
        // Cadena de sólo espacios no tiene tipo reconocible
        assertEquals(SqlValidator.TipoQuery.DESCONOCIDO, SqlValidator.tipo("   "));
    }

    @Test
    void tipo_explain_retornaDesconocido() {
        // EXPLAIN no es un tipo reconocido por SqlValidator
        assertEquals(SqlValidator.TipoQuery.DESCONOCIDO, SqlValidator.tipo("EXPLAIN SELECT * FROM libros"));
    }

    // -------------------------------------------------------------------------
    // esLectura()
    // -------------------------------------------------------------------------

    @Test
    void esLectura_conSelect_retornaTrue() {
        // Solo SELECT es una consulta de lectura
        assertTrue(SqlValidator.esLectura("SELECT 1"));
    }

    @Test
    void esLectura_conInsert_retornaFalse() {
        // INSERT no es lectura
        assertFalse(SqlValidator.esLectura("INSERT INTO libros (id) VALUES (1)"));
    }

    // -------------------------------------------------------------------------
    // esDML()
    // -------------------------------------------------------------------------

    @Test
    void esDML_paraInsertUpdateDelete_retornaTrue() {
        // INSERT, UPDATE y DELETE son DML
        assertTrue(SqlValidator.esDML("INSERT INTO libros (id) VALUES (1)"));
        assertTrue(SqlValidator.esDML("UPDATE libros SET titulo='a' WHERE id=1"));
        assertTrue(SqlValidator.esDML("DELETE FROM libros WHERE id=1"));
    }

    @Test
    void esDML_paraSelectYDDL_retornaFalse() {
        // SELECT, CREATE, DROP y ALTER no son DML
        assertFalse(SqlValidator.esDML("SELECT * FROM libros"));
        assertFalse(SqlValidator.esDML("CREATE TABLE libros (id INT)"));
        assertFalse(SqlValidator.esDML("DROP TABLE libros"));
        assertFalse(SqlValidator.esDML("ALTER TABLE libros ADD COLUMN autor TEXT"));
    }

    // -------------------------------------------------------------------------
    // esDDL()
    // -------------------------------------------------------------------------

    @Test
    void esDDL_paraCreateDropAlter_retornaTrue() {
        // CREATE, DROP y ALTER son DDL
        assertTrue(SqlValidator.esDDL("CREATE TABLE libros (id INT)"));
        assertTrue(SqlValidator.esDDL("DROP TABLE libros"));
        assertTrue(SqlValidator.esDDL("ALTER TABLE libros ADD COLUMN autor TEXT"));
    }

    @Test
    void esDDL_paraSelectYDML_retornaFalse() {
        // SELECT, INSERT, UPDATE y DELETE no son DDL
        assertFalse(SqlValidator.esDDL("SELECT * FROM libros"));
        assertFalse(SqlValidator.esDDL("INSERT INTO libros (id) VALUES (1)"));
        assertFalse(SqlValidator.esDDL("UPDATE libros SET titulo='a' WHERE id=1"));
        assertFalse(SqlValidator.esDDL("DELETE FROM libros WHERE id=1"));
    }

    // -------------------------------------------------------------------------
    // esDestructiva()
    // -------------------------------------------------------------------------

    @Test
    void esDestructiva_delete_retornaTrue() {
        // DELETE siempre es destructivo
        assertTrue(SqlValidator.esDestructiva("DELETE FROM users"));
    }

    @Test
    void esDestructiva_drop_retornaTrue() {
        // DROP siempre es destructivo
        assertTrue(SqlValidator.esDestructiva("DROP TABLE users"));
    }

    @Test
    void esDestructiva_updateSinWhere_retornaTrue() {
        // UPDATE sin WHERE es destructivo porque afecta todas las filas
        assertTrue(SqlValidator.esDestructiva("UPDATE users SET nombre='a'"));
    }

    @Test
    void esDestructiva_updateConWhere_retornaFalse() {
        // UPDATE con WHERE no es destructivo (acotado por condición)
        assertFalse(SqlValidator.esDestructiva("UPDATE users SET nombre='a' WHERE id=1"));
    }

    @Test
    void esDestructiva_updateConWhereCaseInsensitive_retornaFalse() {
        // La detección de WHERE es case-insensitive
        assertFalse(SqlValidator.esDestructiva("update USERS set nombre='a' where id=1"));
    }

    @Test
    void esDestructiva_select_retornaFalse() {
        // SELECT nunca es destructivo
        assertFalse(SqlValidator.esDestructiva("SELECT * FROM users"));
    }

    @Test
    void esDestructiva_insert_retornaFalse() {
        // INSERT no es destructivo
        assertFalse(SqlValidator.esDestructiva("INSERT INTO users (id) VALUES (1)"));
    }
}
