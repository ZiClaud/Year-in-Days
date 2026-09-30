package eu.ziclaud.yearindays.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database implements IDatabase {

    @Override
    public String createDB() {
        String query = """
                DROP TABLE IF EXISTS days;
                DROP TABLE IF EXISTS colours;
                
                CREATE TABLE colours (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                hex TEXT NOT NULL
                );
                
                CREATE TABLE days (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                day DATE NOT NULL UNIQUE,
                colour_id INTEGER NOT NULL,
                repeats BOOL DEFAULT 0,
                description TEXT,
                FOREIGN KEY (colour_id) REFERENCES colours(id)
                );
                
                INSERT INTO colours VALUES ('Default', '#BCBCBCFF');
                INSERT INTO colours VALUES ('Empty', '#00000000');
                """;

        // generated
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:yearindays.db");
             Statement stmt = conn.createStatement()) {

            // Split and execute each statement separately
            String[] statements = query.split(";");
            for (String statement : statements) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                }
            }
            System.out.println("Database created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
            e.printStackTrace();
        }

        return query;
    }

    @Override
    public String insertDays() {
        return """
                """;
    }

    @Override
    public String insertColours() {
        return """
                """;
    }

    @Override
    public String resetDB() {
        return """
                """;
    }

    @Override
    public String _deleteDB() {
        return """
                """;
    }
}
