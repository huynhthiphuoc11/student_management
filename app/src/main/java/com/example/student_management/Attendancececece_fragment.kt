package com.example.student_management

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Attendancececece_fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var btnSelectDate: Button
    private lateinit var tvSelectedDate: TextView
    private lateinit var selectedDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        selectedDate = Calendar.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attendancececece_fragment, container, false)

        btnSelectDate = view.findViewById(R.id.btnSelectDate)
        tvSelectedDate = view.findViewById(R.id.tvSelectedDate)

        btnSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        updateSelectedDate()

        return view
    }

    private fun showDatePickerDialog() {
        val year = selectedDate.get(Calendar.YEAR)
        val month = selectedDate.get(Calendar.MONTH)
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                updateSelectedDate()
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun updateSelectedDate() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        tvSelectedDate.text = "Ngày ${dateFormat.format(selectedDate.time)}"
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Attendancececece_fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
