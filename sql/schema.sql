CREATE DATABASE IF NOT EXISTS consultant_db;
USE consultant_db;

CREATE TABLE IF NOT EXISTS consultants (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    technology VARCHAR(100) NOT NULL,
    experience INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Active'
    );

-- A few sample rows so the dashboard/list pages aren't empty on first run
INSERT INTO consultants (name, email, phone, technology, experience, status) VALUES
                                                                                 ('John Doe', 'john.doe@email.com', '555-0101', 'Java, Spring Boot', 5, 'Active'),
                                                                                 ('Jane Smith', 'jane.smith@email.com', '555-0102', 'Angular, Java', 4, 'Active'),
                                                                                 ('Mike Brown', 'mike.brown@email.com', '555-0103', 'Python, Django', 6, 'Active'),
                                                                                 ('Sarah Lee', 'sarah.lee@email.com', '555-0104', 'Salesforce', 3, 'Inactive'),
                                                                                 ('David Wilson', 'david.wilson@email.com', '555-0105', '.NET, C#', 7, 'Active');