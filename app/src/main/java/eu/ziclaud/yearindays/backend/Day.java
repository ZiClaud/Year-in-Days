package eu.ziclaud.yearindays.backend;

import java.time.LocalDate;
import java.util.Set;

public interface Day {
    // LocalDate day;
    // Colours colour;

    void createDay(LocalDate day, Colour colour);

    Set<Day> getDays(Day days);

    void deleteDay(Day day);
    void deleteDay(LocalDate day);

    void changeColour(Colour colour);
}
