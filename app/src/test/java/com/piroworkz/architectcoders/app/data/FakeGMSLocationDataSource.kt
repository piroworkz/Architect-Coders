package com.piroworkz.architectcoders.app.data

import com.piroworkz.architectcoders.data.source.LocationDataSource

class FakeGMSLocationDataSource(private val defaultCountryCode: String) :
    LocationDataSource {
    override suspend fun findLastRegion(): String? {
        return defaultCountryCode
    }
}