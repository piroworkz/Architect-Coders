package com.piroworkz.architectcoders.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piroworkz.architectcoders.app.data.local.entities.*

@Database(
    entities = [
        DbProduct::class,
        DbBanner::class,
        DbUser::class,
        DbShippingAddress::class,
        DbBillingAddress::class,
        DbPaymentMethod::class,
        DbCartItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val storeDao: StoreDao
    abstract val userDao: UserDao
}