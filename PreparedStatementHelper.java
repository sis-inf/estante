import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementHelper {

    private Connection connection;
    public PreparedStatementHelper(Connection connection) {
        this.connection = connection;
    }
    public ResultSet executeQuery(String sql, Object... params) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error ejecutando query: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Cantidad de parámetros inválida");
        }
    }
}
