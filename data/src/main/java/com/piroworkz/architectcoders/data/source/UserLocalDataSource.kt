package com.piroworkz.architectcoders.data.source

import com.piroworkz.architectcoders.domain.*
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    val userProfile: Flow<UserProfile>

    suspend fun userId(): String?

    suspend fun saveShippingAddress(shippingAddress: ShippingAddress): Error?

    suspend fun saveBillingAddress(billingAddress: BillingAddress): Error?

    suspend fun savePaymentMethod(paymentMethod: PaymentMethod): Error?

    suspend fun saveUserProfile(userProfile: UserProfile): Error?

    suspend fun saveUser(user: UserProfile): Error?

    suspend fun deleteUserProfile(): Error?
}