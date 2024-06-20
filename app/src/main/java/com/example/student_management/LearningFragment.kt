package com.example.student_management

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

class LearningFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MajorAdapter
    private lateinit var tvname: TextView
    private lateinit var tvmajor: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        tvname = view.findViewById(R.id.tvname)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MajorAdapter(emptyList()) // Initialize adapter with an empty list
        recyclerView.adapter = adapter
        loadDataFromApi()
    }

    private fun loadDataFromApi() {
        val userService = UserService.create(requireContext())
        userService.getGradesData().enqueue(object : Callback<List<GradesRequest>> {
            override fun onResponse(
                call: Call<List<GradesRequest>>,
                response: Response<List<GradesRequest>>
            ) {
                if (response.isSuccessful) {
                    val gradeList = response.body()
                    gradeList?.let {
                        if (it.isNotEmpty()) {
                            tvname.text = it[0].nameUser ?: ""
                            adapter = MajorAdapter(it)
                            recyclerView.adapter = adapter
                        } else {
                            Toast.makeText(requireContext(), "Empty list", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Call failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<GradesRequest>>, t: Throwable) {
                Toast.makeText(requireContext(), "Call Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
