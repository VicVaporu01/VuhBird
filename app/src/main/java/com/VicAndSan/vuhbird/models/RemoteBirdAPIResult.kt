package com.VicAndSan.vuhbird.models

data class RemoteBirdAPIResult(
    val entities: List<Entity>,
    val page: Int,
    val pageSize: Int,
    val total: Int
)