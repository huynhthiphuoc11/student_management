package com.example.student_management

data class User(
    val username: String,
    val email: String,
    val role: String, // Role của người dùng (ADMIN, TEACHER, STUDENT),
    val numberPhone: String,
    val nameUser: String,
    val schoolName: String
)

