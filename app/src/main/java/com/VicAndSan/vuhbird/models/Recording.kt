package com.VicAndSan.vuhbird.models

import com.google.gson.annotations.SerializedName

data class Recording(
    val also: List<String>,
    val alt: String,
    @SerializedName("bird-seen") val birdSeen: String,
    val birdId: Int,
    val cnt: String,
    val date: String,
    val en: String,
    @SerializedName("file") val file: String,
    @SerializedName("file-name") val fileName: String,
    val gen: String,
    val id: String,
    val lat: String,
    val length: String,
    val lic: String,
    val lng: String,
    val loc: String,
    @SerializedName("playback-used") val playbackUsed: String,
    val q: String,
    val rec: String,
    val rmk: String,
    val sono: Sono,
    val sp: String,
    val ssp: String,
    val time: String,
    val type: String,
    val uploaded: String,
    val url: String
)