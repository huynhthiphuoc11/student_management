package com.example.student_management

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var major = arrayOf("Toán học", "Vật lý","Hóa học","Sinh Học", "Tin học","Ngữ Văn","Lịch sử","Địa lí","Ngoại ngữ","GDCD","Công nghệ","Môn tự chọn","GDQP")
    private var mark1 = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var mark2 = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var mark3 = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var mark4 = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var mark5 = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var gkmark = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var ckmark = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var tbmmark = arrayOf("9", "9", "9", "9", "9","9","9","9","9","9","9","9","9")
    private var feaback = arrayOf("tốt", "tốt", "tốt", "tốt", "tốt","tốt", "tốt", "tốt", "tốt", "tốt","tốt", "tốt", "tốt")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.cardmajor, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return major.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).itemTvmajor.text = major[position]
        (holder as ViewHolder).itemTvtxmark1.text = mark1[position]
        (holder as ViewHolder).itemTvtxmark2.text = mark2[position]
        (holder as ViewHolder).itemTvtxmark3.text = mark3[position]
        (holder as ViewHolder).itemTvtxmark4.text = mark4[position]
        (holder as ViewHolder).itemTvtxmark5.text = mark5[position]
        (holder as ViewHolder).itemTvgkmark.text = gkmark[position]
        (holder as ViewHolder).itemTvckmark.text = ckmark[position]
        (holder as ViewHolder).itemTvtbmmark.text = tbmmark[position]
        (holder as ViewHolder).itemfeedbackofteacher.text = feaback[position]

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemTvmajor: TextView
        var itemTvtxmark1: TextView
        var itemTvtxmark2: TextView
        var itemTvtxmark3: TextView
        var itemTvtxmark4: TextView
        var itemTvtxmark5: TextView
        var itemTvgkmark: TextView
        var itemTvckmark: TextView
        var itemTvtbmmark: TextView
        var itemfeedbackofteacher: TextView


        init {
            itemTvmajor = itemView.findViewById(R.id.tvmajor)
            itemTvtxmark1 = itemView.findViewById(R.id.tvtxmark1)
            itemTvtxmark2 = itemView.findViewById(R.id.textView2)
            itemTvtxmark3 = itemView.findViewById(R.id.tvtxmark3)
            itemTvtxmark4 = itemView.findViewById(R.id.tvtxmark4)
            itemTvtxmark5 = itemView.findViewById(R.id.tvtxmark5)
            itemTvgkmark = itemView.findViewById(R.id.tvgkmark)
            itemTvckmark = itemView.findViewById(R.id.tvckmark)
            itemTvtbmmark = itemView.findViewById(R.id.tvtbmmark)
            itemfeedbackofteacher = itemView.findViewById(R.id.feedbackofteacher)

        }
    }
}