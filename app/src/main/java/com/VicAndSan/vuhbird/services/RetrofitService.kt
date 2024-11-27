package com.VicAndSan.vuhbird.services

import com.VicAndSan.vuhbird.models.RemoteAPIResult
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitService {
    @GET("v2/birds")
    suspend fun getBirds(
        @Header("API-Key") apiKey: String
    ): RemoteAPIResult
}