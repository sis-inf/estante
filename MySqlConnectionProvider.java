import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionProvider implements ConnectionProvider {

    private String url;
    private String user;
    private String password;

    public MySqlConnectionProvider(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        MySqlConnectionProvider provider =
            new MySqlConnectionProvider("jdbc:mysql://localhost:3306/test", "root", "1234");

        if (provider.getConnection() != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("Error");
        }
    }
}
