package eu.ziclaud.yearindays.backend.database;

import java.time.LocalDate;
import java.util.Set;

import eu.ziclaud.yearindays.stuff.ColourYID;
import eu.ziclaud.yearindays.stuff.DayYID;

public interface IDatabase {
    String createYIDDB();
    Set<DayYID> getDayYID();
    Set<ColourYID> getColoursYID();

    String insertDays(LocalDate day, int colourId, String description);
    String insertColours();
    String resetYIDDB();
    String _deleteYIDDB();
    String createSettingsDB();
}
