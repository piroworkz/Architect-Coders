package com.piroworkz.architectcoders.app.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.piroworkz.architectcoders.app.data.remote.getDocumentById
import com.piroworkz.architectcoders.app.data.remote.saveDocument
import com.piroworkz.architectcoders.app.data.utils.toDomainBillingAddress
import com.piroworkz.architectcoders.app.data.utils.toDomainPaymentMethod
import com.piroworkz.architectcoders.app.data.utils.toDomainShippingAddress
import com.piroworkz.architectcoders.app.data.utils.toDomainUser
import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.data.source.UserRemoteDataSource
import com.piroworkz.architectcoders.data.source.UserRemoteDataSource.Profile.*
import com.piroworkz.architectcoders.domain.*
import javax.inject.Inject

class UserFireStoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRemoteDataSource {

    override suspend fun userExists(email: String): Boolean {
        return firestore.getDocumentById(USERS.path, email)?.exists()!!
    }

    override suspend fun createNewUserAccount(user: User): Error? =
        firestore.saveDocument(USERS.path, user.email, user)

    override suspend fun getUserProfile(email: String): UserProfile? {
        return tryCatch {
            if (userExists(email)) {
                UserProfile(
                    user = firestore.getDocumentById(USERS.path, email).toDomainUser(),
                    shippingAddress = downloadShippingAddress(email),
                    billingAddress = downloadBillingAddress(email),
                    paymentMethod = downloadPaymentMethod(email)
                )
            } else {
                UserProfile(
                    user = firestore.getDocumentById(USERS.path, email).toDomainUser(),
                    shippingAddress = null,
                    billingAddress = null,
                    paymentMethod = null
                )
            }
        }.fold(
            ifLeft = { null },
            ifRight = { it }
        )

    }

    override suspend fun downloadShippingAddress(userId: String): ShippingAddress? =
        firestore.getDocumentById(SHIPPING_ADDRESS.path, userId)
            .toDomainShippingAddress()

    override suspend fun downloadBillingAddress(userId: String): BillingAddress? =
        firestore.getDocumentById(BILLING_ADDRESS.path, userId)
            .toDomainBillingAddress()

    override suspend fun downloadPaymentMethod(userId: String): PaymentMethod? =
        firestore.getDocumentById(PAYMENT_METHOD.path, userId)
            .toDomainPaymentMethod()

    override suspend fun saveShippingAddress(shippingAddress: ShippingAddress): Error? =
        firestore.saveDocument(
            SHIPPING_ADDRESS.path,
            shippingAddress.userId,
            shippingAddress
        )

    override suspend fun saveBillingAddress(billingAddress: BillingAddress): Error? =
        firestore.saveDocument(
            BILLING_ADDRESS.path,
            billingAddress.userId,
            billingAddress
        )

    override suspend fun savePaymentMethod(paymentMethod: PaymentMethod): Error? =
        firestore.saveDocument(
            PAYMENT_METHOD.path,
            paymentMethod.userId,
            paymentMethod
        )

}
