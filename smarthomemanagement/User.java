package smarthomemanagement;

import java.sql.*;
import java.util.Scanner;

public class User {
    private static Scanner scanner = new Scanner(System.in);

    // Method to register a user
    public static void registerUser(Connection conn) {
        try {
            // Taking user details as input from the user
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String query = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
