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
import eu.ziclaud.yearindays.frontend.drawable.createSquareDrawable

class SecondFragment : Fragment() {

    private lateinit var colorPreviewBox: View

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        val density = context.resources.displayMetrics.density

        // 1. Root Container (Vertical stack for settings)
        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            val padding = (16 * density).toInt()
            setPadding(padding, padding, padding, padding)
        }

        val row = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }

        val label = TextView(context).apply {
            text = "Default Square Color"
            textSize = 18f
            layoutParams = LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f)
        }

        colorPreviewBox = View(context).apply {
            val size = (40 * density).toInt()
            layoutParams = LinearLayout.LayoutParams(size, size)

            background = createSquareDrawable(
                DEFAULT_SQUARE_COLOR,
                DEFAULT_SQUARE_STROKE_COLOR
            )

            isClickable = true
            isFocusable = true
        }

        row.addView(label)
        row.addView(colorPreviewBox)
        root.addView(row)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorPreviewBox.setOnClickListener {
            showColorDetailsPopup()
        }
    }

    private fun showColorDetailsPopup() {
        AlertDialog.Builder(requireContext())
            .setTitle("Square Details")
            .setMessage("This is the default color for your year grid.\n\nHex: #BCBCBC")
            .setPositiveButton("Edit") { dialog, _ ->
                // Future: Add color picker logic here
                dialog.dismiss()
            }
            .setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}