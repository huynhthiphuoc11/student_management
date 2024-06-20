package com.example.student_management.api.response

data class SignInResponse(
    val statusCode: Int,
    val message: String,
    val token: String,
    val refreshToken: String,
    val expirationTime: String
)
