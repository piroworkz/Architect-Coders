package com.piroworkz.architectcoders.data.remote.datasource

import arrow.core.Either
import com.piroworkz.architectcoders.data.source.UserRemoteDataSource
import com.piroworkz.architectcoders.domain.*
import javax.inject.Inject

class FakeUserFireStoreDataSource @Inject constructor() : UserRemoteDataSource {
    private var user: User? = null
    private var billingAddress: BillingAddress? = null
    private var shippingAddress: ShippingAddress? = null
    private var paymentMethod: PaymentMethod? = null

    override suspend fun userExists(email: String): Boolean {
        println("CALLED FakeFirestore:userExists ${user?.email}")
        return user?.email?.isNotEmpty() == true
    }

    override suspend fun createNewUserAccount(user: User): Error? = tryCatch {
        println("CALLED FakeFirestore:createNewUserAccount")
        this.user = user
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun getUserProfile(email: String): UserProfile? = tryCatch {
        println("CALLED FakeFirestore:getUserProfile")
        return UserProfile(user, shippingAddress, billingAddress, paymentMethod)
    }.fold(
        ifLeft = { null },
        ifRight = { it }
    )

    override suspend fun downloadShippingAddress(userId: String): ShippingAddress? {
        println("CALLED FakeFirestore:downloadShippingAddress")
        return shippingAddress
    }

    override suspend fun downloadBillingAddress(userId: String): BillingAddress? {
        println("CALLED FakeFirestore:downloadBillingAddress")
        return billingAddress
    }

    override suspend fun downloadPaymentMethod(userId: String): PaymentMethod? {
        println("CALLED FakeFirestore:downloadPaymentMethod")
        return paymentMethod
    }

    override suspend fun saveShippingAddress(shippingAddress: ShippingAddress): Error? = tryCatch {
        println("CALLED FakeFirestore:saveShippingAddress")
        this.shippingAddress = shippingAddress
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun saveBillingAddress(billingAddress: BillingAddress): Error? = tryCatch {
        println("CALLED FakeFirestore:saveBillingAddress")
        this.billingAddress = billingAddress
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun savePaymentMethod(paymentMethod: PaymentMethod): Error? = tryCatch {
        println("CALLED FakeFirestore:savePaymentMethod")
        this.paymentMethod = paymentMethod
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

}

private inline fun <T> tryCatch(block: () -> T): Either<Error, T> =
    try {
        Either.Right(block())
    } catch (e: Exception) {
        Either.Left(Error.Connectivity)
    }