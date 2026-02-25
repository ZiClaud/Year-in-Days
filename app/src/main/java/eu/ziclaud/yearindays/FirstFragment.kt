package eu.ziclaud.yearindays

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import eu.ziclaud.yearindays.databinding.FragmentFirstBinding
import eu.ziclaud.yearindays.logic.DateLogic
import java.time.Year

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
        // Set the current year to the TextView
        binding.currentYearTextView.text = Year.now().value.toString()
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
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_item_white_square, parent, false)
            return SquareViewHolder(view)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
            // position 0 is Day 1, so we compare (position + 1) to the current day
            val dayOfSquare = position + 1

            if (dayOfSquare < DateLogic.currentDayOfYear) {
                // Days that have ALREADY passed: show border
                holder.itemView.setBackgroundResource(R.drawable.white_square)
            } else {
                // Today and future days: show no border
                holder.itemView.setBackgroundResource(R.drawable.transparent_square)
            }
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
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), cols)
        binding.recyclerView.adapter = SquareAdapter(DateLogic.maxDaysInYear)

        /*
        // Button navigation
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        */
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}