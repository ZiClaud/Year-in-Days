package eu.ziclaud.yearindays.drawable

import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import eu.ziclaud.yearindays.DEFAULT_SQUARE_COLOR
import eu.ziclaud.yearindays.DEFAULT_SQUARE_STROKE_COLOR
import eu.ziclaud.yearindays.DEFAULT_TRANSPARENT_SQUARE_COLOR

const val RADIUS = 4f
const val STROKE = 1f

fun createWhiteSquareDrawable(): GradientDrawable {
    return createSquareDrawable(DEFAULT_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
}

fun createTransparentSquareDrawable(): GradientDrawable {
    return createSquareDrawable(DEFAULT_TRANSPARENT_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
}

fun createSquareDrawable(
    fillColor: Int,
    strokeColor: Int = DEFAULT_SQUARE_STROKE_COLOR
): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = RADIUS * Resources.getSystem().displayMetrics.density // 4dp to px
        setColor(fillColor)
        setStroke((STROKE * Resources.getSystem().displayMetrics.density).toInt(), strokeColor)
    }
}