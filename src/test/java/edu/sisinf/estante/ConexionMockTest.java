package edu.sisinf.estante;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Test de ejemplo para verificar que Mockito está configurado correctamente.
 * Demuestra el mockeo de objetos JDBC sin necesidad de base de datos real.
 */
@ExtendWith(MockitoExtension.class)
class ConexionMockTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Test
    void debeMockearObjetosJdbc() throws SQLException {
        // Given
        when(connection.prepareStatement("SELECT 1")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        // When
        PreparedStatement stmt = connection.prepareStatement("SELECT 1");
        ResultSet rs = stmt.executeQuery();

        // Then
        assertNotNull(stmt);
        assertNotNull(rs);
    }
}
