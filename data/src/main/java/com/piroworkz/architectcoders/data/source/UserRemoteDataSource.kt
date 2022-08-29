package com.piroworkz.architectcoders.data.source

import com.piroworkz.architectcoders.domain.*

interface UserRemoteDataSource {
    enum class Profile(val path: String) {
        USERS("Users"),
        BILLING_ADDRESS("BillingAddress"),
        SHIPPING_ADDRESS("ShippingAddress"),
        PAYMENT_METHOD("PaymentMethod"),
    }

    suspend fun userExists(email: String): Boolean

    suspend fun createNewUserAccount(user: User): Error?

    suspend fun getUserProfile(email: String): UserProfile?

    suspend fun downloadShippingAddress(userId: String): ShippingAddress?

    suspend fun downloadBillingAddress(userId: String): BillingAddress?

    suspend fun downloadPaymentMethod(userId: String): PaymentMethod?

    suspend fun saveShippingAddress(shippingAddress: ShippingAddress): Error?

    suspend fun saveBillingAddress(billingAddress: BillingAddress): Error?

    suspend fun savePaymentMethod(paymentMethod: PaymentMethod): Error?
}