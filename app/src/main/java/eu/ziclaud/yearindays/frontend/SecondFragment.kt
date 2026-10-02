package eu.ziclaud.yearindays.frontend

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import eu.ziclaud.yearindays.backend.database.Database2
import eu.ziclaud.yearindays.frontend.drawable.createSquareDrawable
import eu.ziclaud.yearindays.stuff.ColourYID

class SecondFragment : Fragment() {

    private lateinit var colorPreviewBox: View

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        val density = context.resources.displayMetrics.density

        val db: Database2 = Database2(context);
        val colours: Set<ColourYID> = db.getColoursYID();

        // 1. Root Container (Vertical stack for settings)
        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            val padding = (16 * density).toInt()
            setPadding(padding, padding, padding, padding)
        }

        android.util.Log.d("SecondFragment", "Number of colours: ${colours.size}")
        for (colour in colours) {
            android.util.Log.d(
                "SecondFragment", "Colour: ${colour.name}, Hex: ${colour.getHexColourAsInt()}"
            )
            val row = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }

            val label = TextView(context).apply {
                text = colour.name
                textSize = 18f
                layoutParams = LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f)
            }

            /*
            colorPreviewBox = View(context).apply {
                val size = (40 * density).toInt()
                layoutParams = LinearLayout.LayoutParams(size, size)

                background = createSquareDrawable(
                    colour.hexColour.toInt(), colour.hexColourBorder.toInt()
                )

                isClickable = true
                isFocusable = true
            }
            */

            // AI Generated
            val colorPreviewBox = View(context).apply {
                val size = (40 * density).toInt()
                layoutParams = LinearLayout.LayoutParams(size, size)

                background = createSquareDrawable(
                    colour.getHexColourAsInt(), colour.getHexColourBorderAsInt()
                )

                isClickable = true
                isFocusable = true

                // Add the listener HERE
                setOnClickListener {
                    showColorDetailsPopup(colour)
                }
            }

            row.addView(label)
            row.addView(colorPreviewBox)
            root.addView(row)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //colorPreviewBox.setOnClickListener {
        //    showColorDetailsPopup()
        //}
    }

    private fun showColorDetailsPopup(colour: ColourYID) {
        AlertDialog.Builder(requireContext()).setTitle("Square Details")
            .setMessage("This is the default color for your year grid.\n\nHex: " + colour.hexColour)
            .setPositiveButton("Edit") { dialog, _ ->
                // Future: Add color picker logic here
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}