package com.example.student_management

import com.example.student_management.api.request.SignInRequest
import com.example.student_management.api.response.SignInResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentService {
    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.20:8080/auth/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiUser: StudentService = retrofit.create(StudentService::class.java)

    }

    @POST("signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<Void>
    @POST("signin")
    fun signIn(@Body signInRequest: SignInRequest): Call<SignInResponse>
    @POST("logout")
    fun logout(): Call<Void>

}
