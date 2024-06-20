package com.example.student_management

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.student_management.api.request.SignInRequest
import com.example.student_management.api.response.SignInResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmailLogin: EditText
    private lateinit var edtPasswordLogin: EditText
    private lateinit var buttonLogin: Button
    private lateinit var studentService: StudentService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        edtEmailLogin = findViewById(R.id.edtEmailLogin)
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin)
        buttonLogin = findViewById(R.id.buttonLogin)

        // Initialize Retrofit service
        studentService = StudentService.retrofit.create(StudentService::class.java)

        // Set click listener for login button
        buttonLogin.setOnClickListener {
            try {
                validateFields()
                signIn()
            } catch (e: IllegalArgumentException) {
                showToast(e.message ?: "Có lỗi xảy ra")
            }
        }

        // Set click listener for sign up text view
        val tvSignup: TextView = findViewById(R.id.tvsignup)
        tvSignup.setOnClickListener {
            // Start SignUpActivity
            // Note: You should implement SignUpActivity if it's not yet implemented
            // val intent = Intent(this, SignUpActivity::class.java)
            // startActivity(intent)
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateFields() {
        val email = edtEmailLogin.text.toString().trim()
        val password = edtPasswordLogin.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            throw IllegalArgumentException("Vui lòng nhập đầy đủ thông tin")
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw IllegalArgumentException("Email không hợp lệ")
        }
    }

    private fun signIn() {
        val email = edtEmailLogin.text.toString().trim()
        val password = edtPasswordLogin.text.toString().trim()

        val signInRequest = SignInRequest(email, password)

        studentService.signIn(signInRequest).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                if (response.isSuccessful) {
                    val signInResponse = response.body()

                    if (signInResponse != null) {
                        if (signInResponse.statusCode == 200) { // Xác định thành công dựa vào statusCode từ server
                            // Save token to SharedPreferences or other storage mechanism
                            saveToken(signInResponse.token)

                            // Show success message
                            showToast("Đăng nhập thành công")

                            setContentView(R.layout.activity_main)
                            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottom_navigation_home)) { v, insets ->
                                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                                insets
                            }
                            var bot=findViewById<BottomNavigationView>(R.id.bottom_nav)
                            bot.setOnNavigationItemSelectedListener { menuItem ->
                                when (menuItem.itemId) {
                                    R.id.homeId -> {
                                        replaceFragment(BottomNavigation())
                                        return@setOnNavigationItemSelectedListener true
                                    }
                                    R.id.studyId -> {
                                        replaceFragment(LearningFragment())
                                        return@setOnNavigationItemSelectedListener true
                                    }
                                    R.id.profileId -> {
                                        replaceFragment(profile())
                                        return@setOnNavigationItemSelectedListener true
                                    }

                                    else -> false
                                }
                            }
                            replaceFragment(BottomNavigation())
                            // Finish the activity (optional, depends on your flow)
                        } else {
                            showToast("Đăng nhập thất bại: ${signInResponse.message}")
                        }
                    } else {
                        showToast("Đã xảy ra lỗi khi đăng nhập")
                    }
                } else {
                    showToast("Đăng nhập thất bại")
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                showToast("Đã xảy ra lỗi: ${t.message}")
            }
        })
    }



    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.allcom, fragment)
            .commit()
    }
    private fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
