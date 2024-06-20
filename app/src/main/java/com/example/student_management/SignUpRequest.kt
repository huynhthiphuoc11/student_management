package com.example.student_management

data class SignUpRequest(
    val email: String,
    val password: String,
    val role: String
)
