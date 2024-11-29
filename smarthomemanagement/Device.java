package smarthomemanagement;

import java.sql.*;
import java.util.Scanner;

public class Device {
    private static Scanner scanner = new Scanner(System.in);

    // Method to add a device
    public static void addDevice(Connection conn) {
        try {
            // Taking device details as input from the user
            System.out.print("Enter device name: ");
            String deviceName = scanner.nextLine();

            System.out.print("Enter device type: ");
            String deviceType = scanner.nextLine();  // User can input any device type

            System.out.print("Enter device location: ");
            String location = scanner.nextLine();

            System.out.print("Enter user ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            // Check if the user exists
            if (!isUserExists(conn, userId)) {
                System.out.println("Error: User with ID " + userId + " does not exist.");
                return;
            }

            // Inserting the new device into the database
            String query = "INSERT INTO Devices (name, type, status, location, user_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, deviceName);
            stmt.setString(2, deviceType);  // Device type input by the user
            stmt.setString(3, "Off");  // Default status is "Off"
            stmt.setString(4, location);
            stmt.setInt(5, userId);

            // Execute the query to insert the device
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Device added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to check if a user exists
    private static boolean isUserExists(Connection conn, int userId) {
        try {
            String query = "SELECT * FROM Users WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
