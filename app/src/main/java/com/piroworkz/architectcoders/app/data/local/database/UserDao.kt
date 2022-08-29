package com.piroworkz.architectcoders.app.data.local.database

import androidx.room.*
import com.piroworkz.architectcoders.app.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM user_profile")
    fun getUserProfile(): Flow<DbUserProfile>

    @Query("SELECT email FROM user_profile")
    suspend fun getUserId(): String

    @Query("SELECT * FROM billing_address")
    fun getBillingAddress(): Flow<DbBillingAddress>

    @Query("SELECT * FROM shipping_address")
    fun getShippingAddress(): Flow<DbShippingAddress>

    @Query("SELECT * FROM payment_method")
    fun getPaymentMethod(): Flow<DbPaymentMethod>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(dbUser: DbUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBillingAddress(dbBillingAddress: DbBillingAddress)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShippingAddress(dbShippingAddress: DbShippingAddress)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePaymentMethod(dbPaymentMethod: DbPaymentMethod)

    @Query("DELETE FROM user_profile")
    suspend fun deleteUserProfile()

    @Query("DELETE FROM billing_address")
    suspend fun deleteBillingAddress()

    @Query("DELETE FROM shipping_address")
    suspend fun deleteShippingAddress()

    @Query("DELETE FROM payment_method")
    suspend fun deletePaymentMethod()
}


