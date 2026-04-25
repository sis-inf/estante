public class SqlValidator {

    public static boolean isSelect(String query) {
        if (query == null || query.trim().isEmpty()) return false;
        return query.trim().toUpperCase().startsWith("SELECT");
    }

    public static boolean isInsert(String query) {
        if (query == null || query.trim().isEmpty()) return false;
        return query.trim().toUpperCase().startsWith("INSERT");
    }

    public static boolean isUpdate(String query) {
        if (query == null || query.trim().isEmpty()) return false;
        return query.trim().toUpperCase().startsWith("UPDATE");
    }

    public static boolean isDelete(String query) {
        if (query == null || query.trim().isEmpty()) return false;
        return query.trim().toUpperCase().startsWith("DELETE");
    }
}
