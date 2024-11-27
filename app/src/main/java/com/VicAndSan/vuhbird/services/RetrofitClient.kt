package com.VicAndSan.vuhbird.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private const val BASE_URL = "https://nuthatch.lastelm.software/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val birdService: RetrofitService = retrofit.create(RetrofitService::class.java)
}