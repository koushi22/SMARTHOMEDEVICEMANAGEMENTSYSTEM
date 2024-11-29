package smarthomemanagement;

import java.sql.*;
import java.util.Scanner;

public class DeviceCommand {
    private static Scanner scanner = new Scanner(System.in);

    // Method to log device commands
    public static void logDeviceCommand(Connection conn, int deviceId, String command, int userId) {
        try {
            String query = "INSERT INTO DeviceCommands (device_id, command, status, issued_at, response_time, user_id) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, deviceId);
            stmt.setString(2, command);
            stmt.setString(3, "Success"); // Assume the command is successful
            stmt.setInt(4, 500); // Response time in ms
            stmt.setInt(5, userId);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Device command logged successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
