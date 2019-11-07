package io.github.tlaabs.bbaaggeell.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private val BASE_URL = "https://api.github.com/"
    val logger = HttpLoggingInterceptor().apply {
        level = Level.BASIC
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val retrofit: Retrofit
    //services
    val apiService: ApiService

    init {
        this.retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //services init
        apiService = retrofit.create(ApiService::class.java)
    }
}