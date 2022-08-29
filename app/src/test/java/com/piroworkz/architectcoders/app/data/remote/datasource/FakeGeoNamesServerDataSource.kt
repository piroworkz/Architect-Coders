package com.piroworkz.architectcoders.app.data.remote.datasource

import com.piroworkz.architectcoders.data.source.PostalCodesDataSource
import com.piroworkz.architectcoders.domain.PostalAddress

class FakeGeoNamesServerDataSource(
    private val defaultPostalList: List<PostalAddress>
) : PostalCodesDataSource {
    override suspend fun requestAddressByZipCode(
        zipCode: String,
        countryCode: String
    ): List<PostalAddress>? {
        return defaultPostalList
    }

}