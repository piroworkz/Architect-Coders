package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.ShippingAddress
import javax.inject.Inject

class SaveShippingAddressUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(shippingAddress: ShippingAddress): Error? =
        repository.saveShippingAddress(shippingAddress)
}