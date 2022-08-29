package com.piroworkz.architectcoders.app.data.local.database

import com.piroworkz.architectcoders.app.data.local.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


class FakeUserDao(
    userProfile: DbUserProfile
) : UserDao {
    private val user = MutableStateFlow(userProfile.dbUser)
    private val billingAddress = MutableStateFlow(userProfile.billingAddress)
    private val shippingAddress = MutableStateFlow(userProfile.shippingAddress)
    private var paymentMethod = MutableStateFlow(userProfile.paymentMethod)

    override fun getUserProfile(): Flow<DbUserProfile> {
        println("CALLED FakeUserDao:getUserProfile")
        return flowOf(
            DbUserProfile(
                user.value,
                shippingAddress.value,
                billingAddress.value,
                paymentMethod.value
            )
        )
    }

    override suspend fun getUserId(): String {
        println("CALLED FakeUserDao:getUserId")
        return user.value?.email!!
    }

    override suspend fun saveUserProfile(dbUser: DbUser) {
        println("CALLED FakeUserDao:saveUserProfile")
        user.value = dbUser
    }

    override suspend fun deleteUserProfile() {
        println("CALLED FakeUserDao:deleteUserProfile")
        user.value = null
    }

    override fun getBillingAddress(): Flow<DbBillingAddress> {
        println("CALLED FakeUserDao:getBillingAddress")
        return billingAddress.map { it!! }
    }

    override fun getShippingAddress(): Flow<DbShippingAddress> {
        println("CALLED FakeUserDao:getShippingAddress")
        return shippingAddress.map { it!! }
    }

    override fun getPaymentMethod(): Flow<DbPaymentMethod> {
        println("CALLED FakeUserDao:getPaymentMethod")
        return paymentMethod.map { it!! }
    }

    override suspend fun saveBillingAddress(dbBillingAddress: DbBillingAddress) {
        println("CALLED FakeUserDao:saveBillingAddress")
        billingAddress.value = dbBillingAddress
    }

    override suspend fun saveShippingAddress(dbShippingAddress: DbShippingAddress) {
        println("CALLED FakeUserDao:saveShippingAddress")
        shippingAddress.value = dbShippingAddress
    }

    override suspend fun savePaymentMethod(dbPaymentMethod: DbPaymentMethod) {
        println("CALLED FakeUserDao:savePaymentMethod")
        paymentMethod.value = dbPaymentMethod
    }

    override suspend fun deleteBillingAddress() {
        println("CALLED FakeUserDao:deleteBillingAddress")
        billingAddress.value = null
    }

    override suspend fun deleteShippingAddress() {
        println("CALLED FakeUserDao:deleteShippingAddress")
        shippingAddress.value = null
    }

    override suspend fun deletePaymentMethod() {
        println("CALLED FakeUserDao:deletePaymentMethod")
        paymentMethod.value = null
    }

}