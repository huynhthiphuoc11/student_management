package com.example.student_management

import Yasumi
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class AllBenefitFragment : Fragment() {
    private lateinit var imgXinNghi: ImageView
    private lateinit var imgDiemDanh: ImageView
    private lateinit var imgLichHoc: ImageView
    private lateinit var imgDanhBa: ImageView
    private lateinit var imgTinTuc: ImageView
    private lateinit var imgTuyenSinh: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_benefit, container, false)

        imgXinNghi = view.findViewById(R.id.imgxinnghi)
        imgDiemDanh = view.findViewById(R.id.diemdanh)
        imgLichHoc = view.findViewById(R.id.lichhoc)
        imgDanhBa = view.findViewById(R.id.tvphonelist)
        imgTinTuc = view.findViewById(R.id.imgtintuc)
        imgTuyenSinh = view.findViewById(R.id.tintuctuyensinh)

        val clickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.imgxinnghi -> replaceFragment(Yasumi())
                R.id.diemdanh -> replaceFragment(Attendancececece_fragment())
                R.id.lichhoc -> replaceFragment(ScheduleFragment())
                R.id.tvphonelist -> replaceFragment(PhoneNumber())
                R.id.imgtintuc -> replaceFragment(NewsFragment())
                R.id.tintuctuyensinh -> replaceFragment(AdmissionFragment())
            }
        }

        imgXinNghi.setOnClickListener(clickListener)
        imgDiemDanh.setOnClickListener(clickListener)
        imgLichHoc.setOnClickListener(clickListener)
        imgDanhBa.setOnClickListener(clickListener)
        imgTinTuc.setOnClickListener(clickListener)
        imgTuyenSinh.setOnClickListener(clickListener)

        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.allcom, fragment)
            .addToBackStack(null)
            .commit()
    }
}
