package com.piroworkz.architectcoders.data

import com.piroworkz.architectcoders.data.source.LocationDataSource
import com.piroworkz.architectcoders.data.source.PermissionChecker
import com.piroworkz.architectcoders.data.source.PostalCodesDataSource
import com.piroworkz.architectcoders.domain.PostalAddress
import javax.inject.Inject

class GeoPostalRepository @Inject constructor(
    private val postalCodesDataSource: PostalCodesDataSource,
    private val locationDataSource: LocationDataSource,
    private val checker: PermissionChecker
) {
    companion object {
        const val DEFAULT_REGION = "MX"
    }

    suspend fun requestAddressByZipCode(zipCode: String): List<PostalAddress>? {
        return postalCodesDataSource.requestAddressByZipCode(zipCode, findLastCountryCode())
    }

    private suspend fun findLastCountryCode(): String {
        return if (checker.check(PermissionChecker.Permission.COARSE_LOCATION)) {
            locationDataSource.findLastRegion() ?: DEFAULT_REGION
        } else {
            DEFAULT_REGION
        }
    }
}

