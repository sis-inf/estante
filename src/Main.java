public class Main {
    public static void main(String[] args) {
        // Pruebas de validación SQL
        System.out.println(SqlValidator.isSelect("select * from users"));
        System.out.println(SqlValidator.isInsert(" insert into users"));
        System.out.println(SqlValidator.isUpdate("update users set name='a'"));
        System.out.println(SqlValidator.isDelete("delete from users"));
        System.out.println(SqlValidator.isSelect("drop table users"));
    }
}