package com.example.student_management

import Yasumi
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomNavigation : Fragment() {
    private lateinit var tvsdt: TextView
    private lateinit var tvname: TextView
    private lateinit var tvclass: TextView
    private lateinit var tvphonelist: ImageView
    private lateinit var imgxinnghi: ImageView
    private lateinit var diemdanh: ImageView
    private lateinit var lichhoc: ImageView
    private lateinit var tvall: TextView
    private lateinit var imgall: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false)

        tvsdt = view.findViewById(R.id.tvsdt)
        tvname = view.findViewById(R.id.tvname)
        tvclass = view.findViewById(R.id.tvschoolname)
        tvphonelist = view.findViewById(R.id.tvphonelist)
        imgxinnghi = view.findViewById(R.id.imgxinnghi)
        diemdanh = view.findViewById(R.id.diemdanh)
        lichhoc = view.findViewById(R.id.lichhoc)
        tvall = view.findViewById(R.id.tvall)
        imgall = view.findViewById(R.id.imgall)


        // Set the same click listener for all ImageView elements
        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.tvphonelist -> replaceFragment(PhoneNumber())
                R.id.imgxinnghi -> replaceFragment(Yasumi())
                R.id.diemdanh -> replaceFragment(Attendancececece_fragment())
                R.id.lichhoc -> replaceFragment(ScheduleFragment())
                R.id.tvall->replaceFragment(AllBenefitFragment())
                R.id.imgall->replaceFragment(AllBenefitFragment())

            }
        }

        tvphonelist.setOnClickListener(clickListener)
        imgxinnghi.setOnClickListener(clickListener)
        diemdanh.setOnClickListener(clickListener)
        lichhoc.setOnClickListener(clickListener)
        tvall.setOnClickListener(clickListener)
        imgall.setOnClickListener(clickListener)

        // Gọi API với token từ SharedPreferences
        val userService = UserService.create(requireContext())
        userService.getHomepageData().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val userData = response.body()

                    // Cập nhật giao diện với dữ liệu từ API
                    userData?.let {
                        tvname.text = it.nameUser
                        tvsdt.text = it.numberPhone
                        tvclass.text = it.schoolName
                    }
                } else {
                    Toast.makeText(requireContext(), "Call failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(requireContext(), "Call Failed", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.allcom, fragment)
            .addToBackStack(null)
            .commit()
    }
}
