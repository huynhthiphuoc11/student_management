package com.example.student_management

import com.example.student_management.api.request.SignInRequest
import com.example.student_management.api.response.SignInResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface TeacherService {
    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.20:8080/teacher/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiTeacer: TeacherService = retrofit.create(TeacherService::class.java)

    }
    @POST("signin")
    fun signIn(@Body signInRequest: SignInRequest): Call<SignInResponse>

}