import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.student_management.R
import com.example.student_management.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Yasumi : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var btnSave: Button
    private lateinit var etAttendanceAsk: EditText
    private lateinit var etTitle: EditText
    private lateinit var etAttendanceDate: EditText
    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_yasumi, container, false)

        btnSave = view.findViewById(R.id.submit)
        etAttendanceAsk = view.findViewById(R.id.askforleave)
        etTitle = view.findViewById(R.id.title)
        etAttendanceDate = view.findViewById(R.id.etAttendanceDate)
        tvMessage = view.findViewById(R.id.tvMessage)

        // Thiết lập sự kiện khi người dùng nhấp vào EditText ngày thực
        etAttendanceDate.setOnClickListener {
            showDatePickerDialog()
        }

        btnSave.setOnClickListener {
            val attendanceAsk = etAttendanceAsk.text.toString().trim()
            val title = etTitle.text.toString().trim()
            val attendanceDate = etAttendanceDate.text.toString().trim()

            if (attendanceAsk.isNotEmpty() && title.isNotEmpty() && attendanceDate.isNotEmpty()) {
                // Chuẩn bị dữ liệu AttendanceRequest
                val attendanceRequest = AttendanceRequest(
                    attendance_ask = attendanceAsk,
                    title = title,
                    attendance_date = parseStringToDate(attendanceDate), // Chuyển đổi thành Date
                    studentId = 1 // Thay bằng studentId thực tế của học sinh
                )

                // In ra chuỗi JSON của attendanceRequest
                val jsonAttendanceRequest = Gson().toJson(attendanceRequest)
                println("JSON to send: $jsonAttendanceRequest")

                // Gọi hàm để thực hiện lưu điểm danh
                saveAsk(attendanceRequest)
            } else {
                showMessage("Please fill in all fields")
            }
        }

        return view
    }

    // Phương thức để hiển thị DatePickerDialog và chọn ngày thực
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, yearSelected, monthOfYear, dayOfMonth ->
                // Tạo đối tượng Date từ ngày tháng năm được chọn
                val selectedDate = Calendar.getInstance()
                selectedDate.set(yearSelected, monthOfYear, dayOfMonth)

                // Định dạng ngày để hiển thị trên EditText
                val formattedDate = formatDate(selectedDate.time)
                etAttendanceDate.setText(formattedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    // Phương thức để chuyển đổi chuỗi ngày thành đối tượng Date
    private fun parseStringToDate(dateStr: String): String {
        // Định dạng ngày tháng hiện tại trên giao diện
        val dateFormatDisplay = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        // Định dạng ngày tháng để lưu vào cơ sở dữ liệu
        val dateFormatDatabase = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Parse ngày tháng từ chuỗi hiển thị
        val date = dateFormatDisplay.parse(dateStr)
        // Format lại ngày tháng theo định dạng của cơ sở dữ liệu
        return if (date != null) dateFormatDatabase.format(date) else ""
    }

    // Phương thức để định dạng lại đối tượng Date thành chuỗi ngày
    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }

    // Phương thức để lưu dữ liệu điểm danh
    private fun saveAsk(attendanceRequest: AttendanceRequest) {
        val userService = UserService.create(requireContext())
        userService.saveAttendance(attendanceRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    showMessage("Gửi thành công")
                    // Reset lại các trường dữ liệu sau khi lưu thành công
                    resetFields()
                } else {
                    showMessage("Lỗi rồi huhu")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showMessage("Request failed")
            }
        })
    }

    // Phương thức để hiển thị thông báo
    private fun showMessage(message: String) {
        tvMessage.text = message
        tvMessage.visibility = View.VISIBLE
    }

    // Phương thức để reset lại các trường dữ liệu sau khi lưu thành công
    private fun resetFields() {
        etAttendanceAsk.setText("")
        etTitle.setText("")
        etAttendanceDate.setText("")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Yasumi().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

// Class AttendanceRequest

