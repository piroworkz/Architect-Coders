
package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.PaymentMethod
import javax.inject.Inject

class SavePaymentMethodUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(paymentMethod: PaymentMethod): Error? =
        repository.savePaymentMethod(paymentMethod)
}