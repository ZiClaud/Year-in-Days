package eu.ziclaud.yearindays.backend;

import eu.ziclaud.yearindays.backend.database.Database;
import eu.ziclaud.yearindays.backend.database.IDatabase;

public class MainTest {
    public static void main(String[] args) {
        IDatabase db = new Database();

        db.createDB();
    }
}
