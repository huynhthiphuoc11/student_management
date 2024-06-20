package com.example.student_management

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ScheduleFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var morningScheduleAdapter: ScheduleAdapter
    private lateinit var afternoonScheduleAdapter: ScheduleAdapter
    private lateinit var selectedDate: Calendar

    private val schedules = listOf(
        Schedule(DayOfWeek.MONDAY, "08:00", "09:30", "Toán"),
        Schedule(DayOfWeek.MONDAY, "10:00", "11:30", "Vật lý"),
        Schedule(DayOfWeek.WEDNESDAY, "13:00", "14:30", "Hóa học"),
        Schedule(DayOfWeek.WEDNESDAY, "15:00", "16:30", "Sinh học"),
        Schedule(DayOfWeek.FRIDAY, "08:00", "09:30", "Lịch sử")
    )

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
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        val btnSelectDate: Button = view.findViewById(R.id.btnSelectDate)
        val tvSelectedDate: TextView = view.findViewById(R.id.tvSelectedDate)
        val lvMorningSchedule: ListView = view.findViewById(R.id.lvMorningSchedule)
        val lvAfternoonSchedule: ListView = view.findViewById(R.id.lvAfternoonSchedule)

        btnSelectDate.setOnClickListener {
            showDatePickerDialog(tvSelectedDate)
        }

        updateSelectedDate(tvSelectedDate, lvMorningSchedule, lvAfternoonSchedule)

        return view
    }

    private fun showDatePickerDialog(tvSelectedDate: TextView) {
        val year = selectedDate.get(Calendar.YEAR)
        val month = selectedDate.get(Calendar.MONTH)
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                val lvMorningSchedule: ListView = view?.findViewById(R.id.lvMorningSchedule)!!
                val lvAfternoonSchedule: ListView = view?.findViewById(R.id.lvAfternoonSchedule)!!
                updateSelectedDate(tvSelectedDate, lvMorningSchedule, lvAfternoonSchedule)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun updateSelectedDate(tvSelectedDate: TextView, lvMorningSchedule: ListView, lvAfternoonSchedule: ListView) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        tvSelectedDate.text = "Ngày được chọn là: ${dateFormat.format(selectedDate.time)}"

        val dayOfWeek = DayOfWeek.values()[selectedDate.get(Calendar.DAY_OF_WEEK) - 1]

        val morningSchedules = schedules.filter {
            it.dayOfWeek == dayOfWeek && it.startTime < "12:00"
        }

        val afternoonSchedules = schedules.filter {
            it.dayOfWeek == dayOfWeek && it.startTime >= "12:00"
        }

        morningScheduleAdapter = ScheduleAdapter(requireContext(), morningSchedules)
        afternoonScheduleAdapter = ScheduleAdapter(requireContext(), afternoonSchedules)

        lvMorningSchedule.adapter = morningScheduleAdapter
        lvAfternoonSchedule.adapter = afternoonScheduleAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}