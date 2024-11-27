package com.VicAndSan.vuhbird.models

data class RemoteAPIResult(
    val entities: List<Entity>,
    val page: Int,
    val pageSize: Int,
    val total: Int
)