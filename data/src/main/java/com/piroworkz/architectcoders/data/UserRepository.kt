package com.piroworkz.architectcoders.data

import com.piroworkz.architectcoders.data.source.AuthenticationDatasource
import com.piroworkz.architectcoders.data.source.UserLocalDataSource
import com.piroworkz.architectcoders.data.source.UserRemoteDataSource
import com.piroworkz.architectcoders.domain.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remote: UserRemoteDataSource,
    private val local: UserLocalDataSource,
    private val auth: AuthenticationDatasource
) {
    val userProfile: Flow<UserProfile> = local.userProfile

    suspend fun getUserId(): String? =
        local.userId()

    suspend fun requestUserProfile(email: String): Error? =
        remote.getUserProfile(email)?.let { local.saveUserProfile(it) }

    suspend fun saveShippingAddress(shippingAddress: ShippingAddress): Error? {
        return if (remote.saveShippingAddress(shippingAddress) == null) {
            remote.downloadShippingAddress(shippingAddress.userId)
                ?.let { local.saveShippingAddress(it) }
        } else null
    }

    suspend fun saveBillingAddress(billingAddress: BillingAddress): Error? =
        if (remote.saveBillingAddress(billingAddress) == null) {
            remote.downloadBillingAddress(billingAddress.userId)
                ?.let { local.saveBillingAddress(it) }
        } else null

    suspend fun savePaymentMethod(paymentMethod: PaymentMethod): Error? =
        if (remote.savePaymentMethod(paymentMethod) == null) {
            remote.downloadPaymentMethod(paymentMethod.userId)
                ?.let { local.savePaymentMethod(it) }
        } else null

    suspend fun getUserFromFirebase(): User? = auth.getUserFromFirebase()

    suspend fun createNewUserAccount(user: User): Error? {
        return if (remote.userExists(user.email)) {
            remote.getUserProfile(user.email)?.let { local.saveUser(it) }
        } else {
            remote.createNewUserAccount(user)
            remote.getUserProfile(user.email)?.let { local.saveUser(it) }
        }

    }

    suspend fun signOut(): Error? =
        if (local.deleteUserProfile() == null) auth.signOut() else null

    fun checkAuthProvider(): Boolean = auth.checkAuthProvider()
}