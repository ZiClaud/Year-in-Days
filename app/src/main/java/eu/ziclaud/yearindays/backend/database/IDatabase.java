package eu.ziclaud.yearindays.backend.database;

public interface IDatabase {
    String createDB();
    String insertDays();
    String insertColours();
    String resetDB();
    String _deleteDB();
}
