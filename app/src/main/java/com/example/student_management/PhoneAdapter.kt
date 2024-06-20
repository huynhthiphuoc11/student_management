package com.example.student_management

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PhoneAdapter(private var phoneList: List<User>) :
    RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {

    class PhoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvname)
        val phoneTextView: TextView = itemView.findViewById(R.id.tvkq1)
        val callIcon: ImageView = itemView.findViewById(R.id.icon_call)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_phone, parent, false)
        return PhoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        val user = phoneList[position]
        holder.nameTextView.text = user.nameUser
        holder.phoneTextView.text = user.numberPhone ?: "N/A"

        // Xử lý sự kiện click vào icon gọi điện
        holder.phoneTextView.setOnClickListener {
            if (!user.numberPhone.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${user.numberPhone}")
                }
                holder.itemView.context.startActivity(intent)
            } else {
                Toast.makeText(
                    holder.itemView.context,
                    "Số điện thoại không hợp lệ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getItemCount() = phoneList.size

    fun updateList(newPhoneList: List<User>) {
        phoneList = newPhoneList
        notifyDataSetChanged()
    }
}
