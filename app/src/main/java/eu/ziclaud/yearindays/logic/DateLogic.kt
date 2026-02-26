package eu.ziclaud.yearindays.logic

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Year


@RequiresApi(Build.VERSION_CODES.O)
object DateLogic {

    private val currentDate: LocalDate
        get() = LocalDate.now()

    val currentYear: Int
        get() = currentDate.year

    val currentDayOfYear: Int
        get() = currentDate.dayOfYear

    val isLeapYear: Boolean
        get() = Year.isLeap(currentYear.toLong())

    val maxDaysInYear: Int
        get() = if (isLeapYear) 366 else 365

    fun getPosFromDay(date: LocalDate): Int {
        return date.dayOfYear
    }
}