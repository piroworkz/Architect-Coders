package com.piroworkz.architectcoders.data.source

import com.piroworkz.architectcoders.domain.PostalAddress

interface PostalCodesDataSource {
    suspend fun requestAddressByZipCode(zipCode: String, countryCode: String): List<PostalAddress>?
}