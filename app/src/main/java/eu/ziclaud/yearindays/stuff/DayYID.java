package eu.ziclaud.yearindays.stuff;

import static eu.ziclaud.yearindays.frontend.ConstValuesKt.DEFAULT_SQUARE_COLOR;
import static eu.ziclaud.yearindays.frontend.ConstValuesKt.DEFAULT_SQUARE_STROKE_COLOR;

import java.time.LocalDate;

import eu.ziclaud.yearindays.backend.Day;
import eu.ziclaud.yearindays.frontend.ConstValuesKt;

public class DayYID { // TODO implements Day
    LocalDate day;
    // ColourYID colour;
    String hexColour;
    String hexColourBorder;
    String description;

    public DayYID(LocalDate day, String hexColour, String hexColourBorder, String description) {
        this.day = day;
        this.hexColour = hexColour;
        this.hexColourBorder = hexColourBorder;
        this.description = description;

        // TODO?
        // this.colour = new ColourYID();
        // this.colour.hexColour = hexColour;
        // this.colour.hexColourBorder = hexColourBorder;
    }

    public DayYID() {
        // TODO
        //this.hexColour = String.valueOf(ConstValuesKt.DEFAULT_SQUARE_COLOR);
        //this.hexColourBorder = String.valueOf(ConstValuesKt.DEFAULT_SQUARE_STROKE_COLOR);
    }

    public LocalDate getDay() {
        return day;
    }

    public String getHexColour() {
        return hexColour;
    }

    public String getHexColourBorder() {
        return hexColourBorder;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "DayYID{" +
                "day=" + day +
                ", hexColour='" + hexColour + '\'' +
                ", hexColourBorder='" + hexColourBorder + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}