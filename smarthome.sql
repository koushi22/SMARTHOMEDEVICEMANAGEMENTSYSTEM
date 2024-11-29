USE smart_home;
DROP DATABASE IF EXISTS smart_home;
CREATE DATABASE smart_home;
-- Create the Users table
CREATE TABLE IF NOT EXISTS Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,        -- Unique identifier for each user
    username VARCHAR(50) NOT NULL,                  -- Username for login
    email VARCHAR(100) NOT NULL,                    -- User's email address
    password VARCHAR(100) NOT NULL,                 -- User's encrypted password
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Account creation timestamp
);

-- Create the Devices table
CREATE TABLE IF NOT EXISTS Devices (
    device_id INT AUTO_INCREMENT PRIMARY KEY,       -- Unique identifier for each device
    name VARCHAR(100) NOT NULL,                      -- Name of the device (e.g., "Living Room Light")
    type VARCHAR(50) NOT NULL,                       -- Device type (e.g., Light, Thermostat, Camera)
    status VARCHAR(20) DEFAULT 'Off',                -- Device status (e.g., 'On', 'Off', 'Standby')
    location VARCHAR(100),                           -- Device location (e.g., Living Room, Bedroom)
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Last interaction timestamp
    user_id INT,                                     -- Foreign Key: ID of the user who owns the device
    FOREIGN KEY (user_id) REFERENCES Users(user_id)  -- Links to the Users table
);

-- Create the DeviceCommands table
CREATE TABLE IF NOT EXISTS DeviceCommands (
    command_id INT AUTO_INCREMENT PRIMARY KEY,      -- Unique identifier for each command
    device_id INT,                                  -- Foreign Key: ID of the device being controlled
    command VARCHAR(100) NOT NULL,                  -- Command issued (e.g., 'Turn On', 'Adjust Temp')
    status VARCHAR(20) DEFAULT 'Success',           -- Status of the command (e.g., 'Success', 'Failure')
    issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Timestamp when the command was issued
    response_time INT,                              -- Time taken for the device to respond in ms
    user_id INT,                                    -- Foreign Key: ID of the user who issued the command
    FOREIGN KEY (device_id) REFERENCES Devices(device_id), -- Links to Devices table
    FOREIGN KEY (user_id) REFERENCES Users(user_id)         -- Links to Users table
);

-- Create the DeviceLogs table
CREATE TABLE IF NOT EXISTS DeviceLogs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,         -- Unique identifier for each log entry
    device_id INT,                                  -- Foreign Key: ID of the device related to the log
    event VARCHAR(100) NOT NULL,                    -- Description of the event (e.g., 'Motion Detected')
    event_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp of the event
    details TEXT,                                   -- Additional details related to the event
    FOREIGN KEY (device_id) REFERENCES Devices(device_id) -- Links to Devices table
);
