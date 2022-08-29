package com.piroworkz.architectcoders.app.data.local.datasource

import com.piroworkz.architectcoders.app.data.local.database.UserDao
import com.piroworkz.architectcoders.app.data.local.entities.DbUserProfile
import com.piroworkz.architectcoders.app.data.utils.toDatabase
import com.piroworkz.architectcoders.app.data.utils.toDomain
import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.data.source.UserLocalDataSource
import com.piroworkz.architectcoders.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRoomDataSource @Inject constructor(private val userDao: UserDao) : UserLocalDataSource {


    override val userProfile: Flow<UserProfile> =
        userDao.getUserProfile().map(DbUserProfile::toDomain)

    override suspend fun userId(): String? = tryCatch {
        userDao.getUserId()
    }.fold(
        ifLeft = { null },
        ifRight = { it }
    )

    override suspend fun saveShippingAddress(shippingAddress: ShippingAddress): Error? =
        tryCatch {
            userDao.deleteShippingAddress()
            userDao.saveShippingAddress(shippingAddress.toDatabase())
        }.fold(
            ifLeft = { it },
            ifRight = { null }
        )

    override suspend fun saveBillingAddress(billingAddress: BillingAddress): Error? = tryCatch {
        userDao.deleteBillingAddress()
        userDao.saveBillingAddress(billingAddress.toDatabase())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun savePaymentMethod(paymentMethod: PaymentMethod): Error? = tryCatch {
        userDao.deletePaymentMethod()
        userDao.savePaymentMethod(paymentMethod.toDatabase())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun saveUserProfile(userProfile: UserProfile): Error? = tryCatch {
        userProfile.shippingAddress?.let { saveShippingAddress(it) }
        userProfile.billingAddress?.let { saveBillingAddress(it) }
        userProfile.paymentMethod?.let { savePaymentMethod(it) }
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun saveUser(user: UserProfile): Error? = tryCatch {
        deleteUserProfile()
        user.user?.toDatabase()?.let {
            println("<--saveUser  UserRoomDataSource")
            userDao.saveUserProfile(it)
        }
        user.billingAddress?.let { userDao.saveBillingAddress(it.toDatabase()) }
        user.shippingAddress?.let { userDao.saveShippingAddress(it.toDatabase()) }
        user.paymentMethod?.let { userDao.savePaymentMethod(it.toDatabase()) }
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun deleteUserProfile(): Error? = tryCatch {
        userDao.deleteUserProfile()
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

}