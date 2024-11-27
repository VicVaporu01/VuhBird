package com.VicAndSan.vuhbird.services

import com.VicAndSan.vuhbird.models.RemoteAPIResult
import com.VicAndSan.vuhbird.models.RemoteGetBirdByIdResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RetrofitService {
    @GET("v2/birds")
    suspend fun getBirds(
        @Header("API-Key") apiKey: String
    ): RemoteAPIResult

    @GET("birds/{birdId}")
    suspend fun getBird(
        @Header("API-Key") apiKey: String,
        @Path("birdId") birdId: Int
    ): RemoteGetBirdByIdResult
}