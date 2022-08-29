package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.domain.BillingAddress
import com.piroworkz.architectcoders.domain.Error
import javax.inject.Inject

class SaveBillingAddressUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(billingAddress: BillingAddress): Error? =
        repository.saveBillingAddress(billingAddress)
}

