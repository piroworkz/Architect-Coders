package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.GeoPostalRepository
import com.piroworkz.architectcoders.domain.PostalAddress
import javax.inject.Inject

class RequestAddressByZipCodeUseCase @Inject constructor(private val repository: GeoPostalRepository) {
    suspend operator fun invoke(zipCode: String): List<PostalAddress>? =
        repository.requestAddressByZipCode(zipCode)
}