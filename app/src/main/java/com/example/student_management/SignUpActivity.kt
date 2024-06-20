package com.example.student_management

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRePassword: EditText
    private lateinit var spinnerRole: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Ánh xạ các view
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextpassword)
        editTextRePassword = findViewById(R.id.editTextRepassword)
        spinnerRole = findViewById(R.id.spinnerRole)
        val buttonSignUp = findViewById<Button>(R.id.buttonsignup)

        // Thiết lập sự kiện cho nút Đăng ký
        buttonSignUp.setOnClickListener {
            try {
                validateFields() // Kiểm tra xem các trường đã được nhập đầy đủ và chính xác không
                signUp() // Gọi hàm đăng ký nếu các trường đã được nhập đầy đủ và chính xác
            } catch (e: IllegalArgumentException) {
                // Hiển thị thông báo lỗi nếu có trường nào đó không hợp lệ
                showToast(e.message ?: "Có lỗi xảy ra")
            }
        }

        val tvSignin = findViewById<TextView>(R.id.tvsignin)
        tvSignin.setOnClickListener {
            // Chuyển sang giao diện đăng nhập
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        val roles = arrayOf("Nhấp vào để chọn vai trò", "STUDENT", "TEACHER")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter
    }

    private fun validateFields() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val rePassword = editTextRePassword.text.toString().trim()
        val role = spinnerRole.selectedItem.toString()

        if (email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || role == "Nhấp vào để chọn vai trò") {
            throw IllegalArgumentException("Vui lòng nhập đầy đủ thông tin")
        }

        if (password != rePassword) {
            throw IllegalArgumentException("Mật khẩu và xác nhận mật khẩu không khớp")
        }

        // Các kiểm tra khác nếu cần
    }

    private fun signUp() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val role = spinnerRole.selectedItem.toString()

        // Tạo đối tượng SignUpRequest từ các thông tin đã nhập
        val signUpRequest = SignUpRequest(email, password, role)

        // Gửi request đăng ký tới server sử dụng Retrofit
        val call = StudentService.apiUser.signUp(signUpRequest)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Đăng ký thành công
                    showToast("Đăng ký thành công")
                    // Chuyển sang giao diện đăng nhập hoặc thực hiện các thao tác khác
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Đóng activity hiện tại nếu cần
                } else {
                    // Đăng ký thất bại, xử lý thông báo lỗi từ server nếu cần
                    showToast("Đăng ký thất bại")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Xử lý khi gặp lỗi trong quá trình gửi request
                showToast("Đã xảy ra lỗi: " + t.message)
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
