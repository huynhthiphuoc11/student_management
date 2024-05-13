package com.example.student_management
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MajorAdapter(private val dataList: List<String>) :
    RecyclerView.Adapter<MajorAdapter.MajorViewHolder>() {

    class MajorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tvmajor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MajorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardmajor, parent, false)
        return MajorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MajorViewHolder, position: Int) {
        val item = dataList[position]
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
