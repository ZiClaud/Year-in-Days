package eu.ziclaud.yearindays

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import eu.ziclaud.yearindays.databinding.FragmentFirstBinding
import java.time.LocalDate
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
        // Get current year and day of year
        val currentDate = LocalDate.now()
        val currentYear = currentDate.year
        val currentDayOfYear = currentDate.dayOfYear
        val isLeapYear = Year.isLeap(currentYear.toLong())
        val maxDaysInYear = if (isLeapYear) 366 else 365

        // Set the text to currentDayTextView
        binding.currentDayTextView.text = "$currentDayOfYear/$maxDaysInYear"
    }

    inner class SquareAdapter(private val count: Int) :
        RecyclerView.Adapter<SquareAdapter.SquareViewHolder>() {

        inner class SquareViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_item_white_square, parent, false)
            return SquareViewHolder(view)
        }

        override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
            // Here you could change the color of squares based on 'position'
            // e.g., if (position < currentDay) holder.itemView.setBackgroundColor(...)
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
        val currentDayOfYear = LocalDate.now().dayOfYear
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), cols)
        binding.recyclerView.adapter = SquareAdapter(currentDayOfYear)

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