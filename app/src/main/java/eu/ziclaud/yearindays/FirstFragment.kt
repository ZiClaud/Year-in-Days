package eu.ziclaud.yearindays

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.ziclaud.yearindays.drawable.createSquareDrawable
import eu.ziclaud.yearindays.drawable.createTransparentSquareDrawable
import eu.ziclaud.yearindays.drawable.createWhiteSquareDrawable
import eu.ziclaud.yearindays.logic.DateLogic
import java.time.LocalDate

class FirstFragment : Fragment() {

    private lateinit var currentYearTextView: TextView
    private lateinit var currentDayTextView: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        val density = context.resources.displayMetrics.density

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            val padding = (16 * density).toInt()
            setPadding(padding, padding, padding, padding)
        }

        currentYearTextView = TextView(context).apply {
            textSize = 42f
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }

        currentDayTextView = TextView(context).apply {
            textSize = 16f
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }

        recyclerView = RecyclerView(context).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 0, 1f).apply {
                val margin = (16 * density).toInt()
                setMargins(0, margin, 0, 0)
            }
        }

        root.addView(currentYearTextView)
        root.addView(currentDayTextView)
        root.addView(recyclerView)

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setYearField()
        setDayField()

        val cols = 14
        recyclerView.layoutManager = GridLayoutManager(requireContext(), cols)
        recyclerView.adapter = SquareAdapter(DateLogic.maxDaysInYear)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setYearField() {
        currentYearTextView.text = DateLogic.currentYear.toString()
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDayField() {
        val day = DateLogic.currentDayOfYear
        val max = DateLogic.maxDaysInYear
        currentDayTextView.text = "$day/$max"
    }

    inner class SquareAdapter(private val count: Int) :
        RecyclerView.Adapter<SquareAdapter.SquareViewHolder>() {

        inner class SquareViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareViewHolder {
            val density = parent.context.resources.displayMetrics.density
            val view = View(parent.context).apply {
                val size = (SQUARE_SIZE * density).toInt()
                val margin = (SQUARE_MARGIN * density).toInt()

                layoutParams = ViewGroup.MarginLayoutParams(size, size).apply {
                    setMargins(margin, margin, margin, margin)
                }
            }
            return SquareViewHolder(view)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
            val dayOfSquare = position + 1
            val specialDays = intArrayOf(DateLogic.getPosFromDay(LocalDate.of(2001, 11, 24)))
            val specialDaysExam = intArrayOf(
                DateLogic.getPosFromDay(LocalDate.of(2026, 4, 7)),
                DateLogic.getPosFromDay(LocalDate.of(2026, 6, 30))
            )
            val specialDaysRed = intArrayOf(DateLogic.getPosFromDay(LocalDate.of(2026, 7, 15)))

            val baseDrawable = if (dayOfSquare < DateLogic.currentDayOfYear) {
                createWhiteSquareDrawable()
            } else {
                createTransparentSquareDrawable()
            }

            val finalDrawable = if (specialDays.contains(dayOfSquare)) {
                createSquareDrawable(DEFAULT_YELLOW_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
            } else if (specialDaysExam.contains(dayOfSquare)) {
                createSquareDrawable(DEFAULT_GREEN_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
            } else if (specialDaysRed.contains(dayOfSquare)) {
                createSquareDrawable(DEFAULT_RED_SQUARE_COLOR, DEFAULT_SQUARE_STROKE_COLOR)
            } else {
                baseDrawable
            }

            holder.itemView.background = finalDrawable
        }

        override fun getItemCount(): Int = count
    }
}