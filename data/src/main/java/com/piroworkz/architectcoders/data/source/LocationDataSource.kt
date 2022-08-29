package com.piroworkz.architectcoders.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}