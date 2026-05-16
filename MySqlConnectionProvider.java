import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionProvider implements ConnectionProvider {

    private String url;
    private String user;
    private String password;
    private int timeoutSeconds;
    public MySqlConnectionProvider(String url, String user, String password, int timeoutSeconds) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public Connection getConnection() {
        try {
            DriverManager.setLoginTimeout(timeoutSeconds);
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            throw new RuntimeException("Tiempo de conexión excedido");
        }
    }

    public static void main(String[] args) {
        MySqlConnectionProvider provider =
            new MySqlConnectionProvider("jdbc:mysql://localhost:3306/test", "root", "1234", 5);

        if (provider.getConnection() != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("Error");
        }
    }
}
