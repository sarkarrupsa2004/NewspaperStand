import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database credentials
    private static final String URL      = "jdbc:mysql://localhost:3306/newspaper_db";
    private static final String USER     = "root";
    private static final String PASSWORD = "";

    // This method returns a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}