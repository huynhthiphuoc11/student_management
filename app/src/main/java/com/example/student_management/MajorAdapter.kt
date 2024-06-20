package com.example.student_management

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MajorAdapter(private val dataList: List<GradesRequest>) :
    RecyclerView.Adapter<MajorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTvmajor: TextView = itemView.findViewById(R.id.tvmajor)
        val itemTvtxmark1: TextView = itemView.findViewById(R.id.tvtxmark1)
        val itemTvtxmark2: TextView = itemView.findViewById(R.id.tvtxmark2)
        val itemTvtxmark3: TextView = itemView.findViewById(R.id.tvtxmark3)
        val itemTvtxmark4: TextView = itemView.findViewById(R.id.tvtxmark4)
        val itemTvtxmark5: TextView = itemView.findViewById(R.id.tvtxmark5)
        val itemTvgkmark: TextView = itemView.findViewById(R.id.tvgkmark)
        val itemTvckmark: TextView = itemView.findViewById(R.id.tvckmark)
        val itemTvtbmmark: TextView = itemView.findViewById(R.id.tvtbmmark)
        val itemFeedbackofteacher: TextView = itemView.findViewById(R.id.feedbackofteacher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardmajor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.itemTvmajor.text = item.courseName ?: ""
        holder.itemTvtxmark1.text = String.format("%.1f", item.frequentScore1)
        holder.itemTvtxmark2.text = String.format("%.1f", item.frequentScore2)
        holder.itemTvtxmark3.text = String.format("%.1f", item.frequentScore3)
        holder.itemTvtxmark4.text = String.format("%.1f", item.frequentScore4)
        holder.itemTvtxmark5.text = String.format("%.1f", item.frequentScore5)
        holder.itemTvgkmark.text = String.format("%.1f", item.midtermScore)
        holder.itemTvckmark.text = String.format("%.1f", item.finalScore)
        holder.itemTvtbmmark.text = String.format("%.1f", calculateAverage(item))
        holder.itemFeedbackofteacher.text = item.comments ?: ""
    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = dataList[position]
//        holder.itemTvmajor.text = item.courseName ?: ""
//        holder.itemTvtxmark1.text = item.frequentScore1.toString()
//        holder.itemTvtxmark2.text = item.frequentScore2.toString()
//        holder.itemTvtxmark3.text = item.frequentScore3.toString()
//        holder.itemTvtxmark4.text = item.frequentScore4.toString()
//        holder.itemTvtxmark5.text = item.frequentScore5.toString()
//        holder.itemTvgkmark.text = item.midtermScore.toString()
//        holder.itemTvckmark.text = item.finalScore.toString()
//        holder.itemTvtbmmark.text = calculateAverage(item).toString()
//        holder.itemFeedbackofteacher.text = item.comments ?: ""
//    }

    private fun calculateAverage(item: GradesRequest): Double {
        val sum = item.frequentScore1 +
                item.frequentScore2 +
                item.frequentScore3 +
                item.frequentScore4 +
                item.frequentScore5 +
                item.midtermScore*2 +
                item.finalScore*3
        return sum / 10
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
