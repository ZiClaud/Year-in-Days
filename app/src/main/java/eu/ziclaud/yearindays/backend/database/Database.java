package eu.ziclaud.yearindays.backend.database;

import static eu.ziclaud.yearindays.frontend.ConstValuesKt.DEFAULT_SQUARE_COLOR;
import static eu.ziclaud.yearindays.frontend.ConstValuesKt.DEFAULT_SQUARE_STROKE_COLOR;
import static eu.ziclaud.yearindays.frontend.ConstValuesKt.DEFAULT_TRANSPARENT_SQUARE_COLOR;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import eu.ziclaud.yearindays.stuff.ColourYID;
import eu.ziclaud.yearindays.stuff.DayYID;

public class Database implements IDatabase {

    @Override
    public String createYIDDB() {
        String query = resetYIDDB();

        // AI generated
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:yearindays.db"); Statement stmt = conn.createStatement()) {

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Set<DayYID> getDayYID() {
        // AI Generated
        String query = """
                SELECT d.day, c.hex, c.hex_border, d.description
                FROM days d
                JOIN colours c ON d.colour_id = c.id
                ORDER BY d.day
                """;

        Set<DayYID> days = new HashSet<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:yearindays.db"); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                LocalDate day = null;
                day = LocalDate.parse(rs.getString("day"));
                String hexColour = rs.getString("hex");
                String hexColourBorder = rs.getString("hex_border");
                String description = rs.getString("description");

                days.add(new DayYID(day, hexColour, hexColourBorder, description));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all DayYIDs: " + e.getMessage());
            e.printStackTrace();
        }

        return days;
    }

    @Override
    public String insertDays(LocalDate day, int colourId, String description) {
        assert (colourId >= 1);
        // AI Generated
        String query = """
                INSERT INTO days (day, colour_id, description)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:yearindays.db"); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, day.toString());
            pstmt.setInt(2, colourId);
            pstmt.setString(3, description);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Day inserted successfully!");
                return "Success";
            }

        } catch (SQLException e) {
            System.err.println("Error inserting day: " + e.getMessage());
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }

        return "Failed to insert day";
    }

    @Override
    public Set<ColourYID> getColoursYID() {
        String query = """
                SELECT id, name, hex, hex_border
                FROM colours
                """;

        Set<ColourYID> colours = new HashSet<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:yearindays.db"); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String hexColour = rs.getString("hex");
                String hexColourBorder = rs.getString("hex_border");
                String name = rs.getString("name");

                colours.add(new ColourYID(id, hexColour, hexColourBorder, name));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all DayYIDs: " + e.getMessage());
            e.printStackTrace();
        }

        return colours;
    }

    @Override
    public String insertColours() {
        return """
                """;
    }

    @Override
    public String resetYIDDB() {
        return _deleteYIDDB() + """
                CREATE TABLE colours (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                hex TEXT NOT NULL,
                hex_border TEXT NOT NULL DEFAULT '""" + String.format("0x%08X", DEFAULT_SQUARE_STROKE_COLOR) + """
                '
                );
                        CREATE TABLE days (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        day DATE NOT NULL UNIQUE,
                        colour_id INTEGER NOT NULL,
                        repeats BOOL DEFAULT 0,
                        description TEXT,
                        FOREIGN KEY (colour_id) REFERENCES colours(id)
                        );
                """ + "INSERT INTO colours (name, hex) VALUES ('Default', '" + String.format("0x%08X", DEFAULT_SQUARE_COLOR) + "');" + "INSERT INTO colours (name, hex) VALUES ('Empty', '" + String.format("0x%08X", DEFAULT_TRANSPARENT_SQUARE_COLOR) + "');";
    }

    @Override
    public String _deleteYIDDB() {
        return """
                DROP TABLE IF EXISTS days;
                DROP TABLE IF EXISTS colours;
                """;
    }

    @Override
    public String createSettingsDB() {
        return """
                """;
    }
}
