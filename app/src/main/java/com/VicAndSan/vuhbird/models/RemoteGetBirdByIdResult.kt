package com.VicAndSan.vuhbird.models

data class RemoteGetBirdByIdResult(
    val family: String,
    val id: Int,
    val images: List<String>,
    val lengthMax: String,
    val lengthMin: String,
    val name: String,
    val order: String,
    val recordings: List<Recording>,
    val region: List<String>,
    val sciName: String,
    val status: String,
    val wingspanMax: String,
    val wingspanMin: String
)