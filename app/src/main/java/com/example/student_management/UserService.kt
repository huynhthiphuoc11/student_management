package com.example.student_management

import AttendanceRequest
import android.content.Context
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("getHomepage")
    fun getHomepageData(): Call<User>
    @GET("grades")
    fun getGradesData(): Call<List<GradesRequest>>

    @GET("getphonenumbers")
    fun getPhoneNumbers(): Call<List<User>>

    @GET("attendance")
    fun getattendance(): Call<List<AttendanceRequest>>
    @POST("saveask")
    fun saveAttendance(@Body attendanceRequest: AttendanceRequest): Call<Void>
    companion object {
        fun create(context: Context): UserService {
            val retrofit = RetrofitClient.getClient("http://192.168.1.20:8080/student/", context)
            return retrofit.create(UserService::class.java)
        }
    }
}
