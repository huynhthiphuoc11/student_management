package com.example.student_management

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ScheduleAdapter(private val context: Context, private val dataSource: List<Schedule>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.schedule_item, parent, false)
            holder = ViewHolder()
            holder.dayOfWeekTextView = view.findViewById(R.id.tvDayOfWeek) as TextView
            holder.timeTextView = view.findViewById(R.id.tvTime) as TextView
            holder.courseTextView = view.findViewById(R.id.tvCourse) as TextView
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val schedule = getItem(position) as Schedule
        holder.dayOfWeekTextView?.text = schedule.dayOfWeek.name
        holder.timeTextView?.text = "${schedule.startTime} - ${schedule.endTime}"
        holder.courseTextView?.text = schedule.course

        return view
    }

    private class ViewHolder {
        var dayOfWeekTextView: TextView? = null
        var timeTextView: TextView? = null
        var courseTextView: TextView? = null
    }
}
