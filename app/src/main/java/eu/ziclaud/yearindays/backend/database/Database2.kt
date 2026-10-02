package eu.ziclaud.yearindays.backend.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import eu.ziclaud.yearindays.stuff.ColourYID
import eu.ziclaud.yearindays.stuff.DayYID
import java.time.LocalDate

// AI Generated
class Database2(context: Context) : SQLiteOpenHelper(context, "yearindays.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE colours (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                hex TEXT,
                hex_border TEXT
            )
        """
        )

        db.execSQL(
            """
            CREATE TABLE days (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                day TEXT,
                colour_id INTEGER,
                description TEXT,
                FOREIGN KEY(colour_id) REFERENCES colours(id)
            )
        """
        )

        db.execSQL("INSERT INTO colours (name, hex, hex_border) VALUES ('Default', '0xFFFFFFFF', '0xFF000000')")
        db.execSQL("INSERT INTO colours (name, hex, hex_border) VALUES ('Empty', '0x00000000', '0xFF000000')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun getColoursYID(): Set<ColourYID> {
        val colours = mutableSetOf<ColourYID>()
        val db = readableDatabase
        val cursor = db.query("colours", null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val hex = cursor.getString(cursor.getColumnIndexOrThrow("hex"))
            val hexBorder = cursor.getString(cursor.getColumnIndexOrThrow("hex_border"))

            colours.add(ColourYID(id, hex, hexBorder, name))
        }
        cursor.close()
        return colours
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getDayYID(): Set<DayYID> {
        val query = """
            SELECT d.day, c.hex, c.hex_border, d.description
            FROM days d
            JOIN colours c ON d.colour_id = c.id
            ORDER BY d.day
        """.trimIndent()

        val days = mutableSetOf<DayYID>()
        val db = readableDatabase

        try {
            val cursor = db.rawQuery(query, null)

            while (cursor.moveToNext()) {
                val dayString = cursor.getString(cursor.getColumnIndexOrThrow("day"))
                val day = LocalDate.parse(dayString)
                val hexColour = cursor.getString(cursor.getColumnIndexOrThrow("hex"))
                val hexColourBorder = cursor.getString(cursor.getColumnIndexOrThrow("hex_border"))
                val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))

                days.add(DayYID(day, hexColour, hexColourBorder, description))
            }
            cursor.close()
        } catch (e: Exception) {
            System.err.println("Error fetching all DayYIDs: ${e.message}")
            e.printStackTrace()
        }

        return days
    }
}
