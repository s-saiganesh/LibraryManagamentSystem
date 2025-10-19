import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root"; // or your MySQL username
    private static final String PASSWORD = "Ganesh@2006."; // change this

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
