import edu.sisinf.estante.util.SqlValidator;

public class Main {
    public static void main(String[] args) {
        System.out.println(SqlValidator.tipo("select * from t"));
        System.out.println(SqlValidator.tipo("CREATE TABLE x (id INT)"));
        System.out.println(SqlValidator.esDestructiva("DELETE FROM users"));
        System.out.println(SqlValidator.esDestructiva("UPDATE users SET nombre='a'"));
        System.out.println(SqlValidator.esDestructiva("UPDATE users SET nombre='a' WHERE id=1"));
    }
}