package com.piroworkz.architectcoders.app.data.remote.datasource

import com.piroworkz.architectcoders.app.data.remote.GeoService
import com.piroworkz.architectcoders.app.data.remote.model.RemotePostalAddress
import com.piroworkz.architectcoders.app.data.utils.toDomainPostalCode
import com.piroworkz.architectcoders.app.di.GeoUserName
import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.data.source.PostalCodesDataSource
import com.piroworkz.architectcoders.domain.PostalAddress
import javax.inject.Inject

class GeoNamesServerDataSource @Inject constructor(
    @GeoUserName private val userName: String,
    private val geoService: GeoService,
) : PostalCodesDataSource {

    override suspend fun requestAddressByZipCode(
        zipCode: String,
        countryCode: String
    ): List<PostalAddress>? =
        tryCatch {
            geoService.requestAddressByZipCode(zipCode, countryCode, userName)
                .postalCodes
                .map(RemotePostalAddress::toDomainPostalCode)
                .ifEmpty { null }
        }.fold(
            ifLeft = { null },
            ifRight = { it }
        )
}