package com.example.student_management

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhoneNumber.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhoneNumber :  Fragment() {
    private lateinit var phoneRecyclerView: RecyclerView
    private lateinit var phoneAdapter: PhoneAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_phone_number, container, false)

        phoneRecyclerView = view.findViewById(R.id.phoneRecyclerView)
        phoneRecyclerView.layoutManager = LinearLayoutManager(context)
        phoneAdapter = PhoneAdapter(emptyList())
        phoneRecyclerView.adapter = phoneAdapter

        fetchPhoneNumbers()

        return view
    }

    private fun fetchPhoneNumbers() {
        val userService = UserService.create(requireContext())
        userService.getPhoneNumbers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val phoneList = response.body() ?: emptyList()
                    phoneAdapter.updateList(phoneList)
                } else {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(context, "Request failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onPhoneNumberClick(view: View) {
        if (view is TextView) {
            val phoneNumber = view.text.toString()

            if (phoneNumber.isNotEmpty()) {
                // Mở ứng dụng gọi điện với số điện thoại đã điền sẵn
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}