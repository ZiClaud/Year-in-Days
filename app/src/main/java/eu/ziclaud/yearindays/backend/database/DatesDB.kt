package eu.ziclaud.yearindays.backend.database
/*
import android.os.Build
import androidx.annotation.RequiresApi
import eu.ziclaud.yearindays.frontend.DEFAULT_GREEN_SQUARE_COLOR
import eu.ziclaud.yearindays.frontend.DEFAULT_RED_SQUARE_COLOR
import eu.ziclaud.yearindays.frontend.DEFAULT_SQUARE_STROKE_COLOR
import eu.ziclaud.yearindays.frontend.DEFAULT_YELLOW_SQUARE_COLOR
import eu.ziclaud.yearindays.frontend.createSquareDrawable


@RequiresApi(Build.VERSION_CODES.O)
class DatesDB {
    /*
    val dayOfSquare = position + 1

    val specialDays = intArrayOf(DateLogic.getPosFromDay(LocalDate.of(2001, 11, 24)))
    val specialDaysExam = intArrayOf(
        DateLogic.getPosFromDay(LocalDate.of(2026, 4, 7)),
        DateLogic.getPosFromDay(LocalDate.of(2026, 6, 30))
    )
    val specialDaysRed = intArrayOf(DateLogic.getPosFromDay(LocalDate.of(2026, 7, 15)))
    */
    val finalDrawable = if (specialDays.contains(dayOfSquare)) {
        createSquareDrawable(DEFAULT_YELLOW_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
    } else if (specialDaysExam.contains(dayOfSquare)) {
        createSquareDrawable(DEFAULT_GREEN_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
    } else if (specialDaysRed.contains(dayOfSquare)) {
        createSquareDrawable(DEFAULT_RED_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
    } else {
        baseDrawable
    }

    fun _createDB() {

    }

    fun getSQLDaysAndColours() {

    }

    fun _getSQLDays() {

    }

    fun _getSQLColor() {

    }
}
*/