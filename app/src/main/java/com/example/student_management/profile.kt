package com.example.student_management

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class profile : Fragment() {

    private lateinit var tvlogout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Retrieve any arguments here if needed
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        tvlogout = view.findViewById(R.id.tvlogout)
        tvlogout.setOnClickListener {
            logout()
        }
        return view
    }

    private fun logout() {
        val userService = StudentService.apiUser
        userService.logout().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Clear the token from SharedPreferences
                    val sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.remove("token")
                    editor.apply()

                    // Navigate to the login activity
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Logout failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(requireContext(), "Logout Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profile().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }
}
