package smarthomemanagement;

import java.sql.*;

public class DatabaseUtil {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/smart_home"; // Update your database name here
        String username = "root"; 
        String password = "K_koushi@22";
        return DriverManager.getConnection(url, username, password);
    }
}
