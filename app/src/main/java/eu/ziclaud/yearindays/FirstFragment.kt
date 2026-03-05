package eu.ziclaud.yearindays

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import eu.ziclaud.yearindays.databinding.FragmentFirstBinding
import eu.ziclaud.yearindays.drawable.createSquareDrawable
import eu.ziclaud.yearindays.drawable.createTransparentSquareDrawable
import eu.ziclaud.yearindays.drawable.createWhiteSquareDrawable
import eu.ziclaud.yearindays.logic.DateLogic
import java.time.LocalDate


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setYearField() {
        binding.currentYearTextView.text = DateLogic.currentYear.toString()
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDayField() {
        val day = DateLogic.currentDayOfYear
        val max = DateLogic.maxDaysInYear

        binding.currentDayTextView.text = "$day/$max"
    }

    inner class SquareAdapter(private val count: Int) :
        RecyclerView.Adapter<SquareAdapter.SquareViewHolder>() {

        inner class SquareViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareViewHolder {
            val context = parent.context
            val density = context.resources.displayMetrics.density

            val view = View(context).apply {
                val size = (SQUARE_SIZE * density).toInt()
                val margin = (SQUARE_MARGIN * density).toInt()

                val params = ViewGroup.MarginLayoutParams(size, size).apply {
                    setMargins(margin, margin, margin, margin)
                }
                layoutParams = params

                background = createTransparentSquareDrawable()
            }

            return SquareViewHolder(view)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun setupSquares(holder: SquareViewHolder, position: Int) {
            if (position < DateLogic.currentDayOfYear) {
                holder.itemView.background = createWhiteSquareDrawable()
            } else {
                holder.itemView.background = createTransparentSquareDrawable()
            }
        }

        /*
                @RequiresApi(Build.VERSION_CODES.O)
                private fun setupSpecialSquares(holder: SquareViewHolder, position: Int, specialDays: IntArray){
                    if (specialDays.contains(position)) {
                        val drawable = ContextCompat.getDrawable(holder.itemView.context, R.drawable.white_square) as? GradientDrawable

                        drawable?.apply {
                            mutate()
                            setColor(DEFAULT_YELLOW_SQUARE_COLOR)
                            val thickness = (1 * holder.itemView.context.resources.displayMetrics.density).toInt()
                            setStroke(thickness, DEFAULT_SQUARE_STROKE_COLOR)
                        }

                        holder.itemView.background = drawable
                    }
                }
        */
        @RequiresApi(Build.VERSION_CODES.O)
        private fun setupSpecialSquares(
            holder: SquareViewHolder,
            position: Int,
            specialDays: IntArray
        ) {
            if (specialDays.contains(position)) {
                // Just call the styling function here
                holder.itemView.background = createSquareDrawable(
                    DEFAULT_YELLOW_SQUARE_COLOR,
                    DEFAULT_SQUARE_STROKE_COLOR
                )
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
            // position 0 is Day 1, so we compare (position + 1) to the current day
            val dayOfSquare: Int = position + 1
            val specialDays: IntArray =
                intArrayOf(DateLogic.getPosFromDay(LocalDate.of(2001, 11, 24)))

            setupSquares(holder, dayOfSquare)
            setupSpecialSquares(holder, dayOfSquare, specialDays)
        }

        override fun getItemCount(): Int = count
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setYearField()
        setDayField()

        // Setup RecyclerView with a Grid Manager
        val cols = 14
        binding.recyclerView.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(requireContext(), cols)
        binding.recyclerView.adapter = SquareAdapter(DateLogic.maxDaysInYear)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}