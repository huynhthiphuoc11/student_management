package com.example.student_management

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TokenInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "") ?: ""

        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}

object RetrofitClient {
    private lateinit var retrofit: Retrofit

    fun getClient(baseUrl: String, context: Context): Retrofit {
        if (!::retrofit.isInitialized) {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor(context))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
