package com.example.student_management
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LearningFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MajorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataList = listOf("Toán học", "Vật lý","Hóa học","Sinh Học", "Tin học","Ngữ Văn","Lịch sử","Địa lí","Ngoại ngữ","GDCD","Công nghệ","Môn tự chọn","GDQP")
        adapter = MajorAdapter(dataList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}
