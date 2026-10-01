package eu.ziclaud.yearindays.backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.Set;

import eu.ziclaud.yearindays.backend.database.Database;
import eu.ziclaud.yearindays.backend.database.IDatabase;
import eu.ziclaud.yearindays.stuff.ColourYID;
import eu.ziclaud.yearindays.stuff.DayYID;

public class MainTest {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        IDatabase db = new Database();

        db.createYIDDB();

        db.insertDays(LocalDate.ofYearDay(24, 11), 1, "test");

        Set<DayYID> days = db.getDayYID();
        System.out.println(days);

        Set<ColourYID> colour = db.getColoursYID();
        System.out.println(colour);
    }
}
