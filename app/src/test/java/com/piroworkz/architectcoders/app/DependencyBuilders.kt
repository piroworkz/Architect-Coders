package com.piroworkz.architectcoders.app

import com.piroworkz.architectcoders.app.data.local.database.FakeStoreDao
import com.piroworkz.architectcoders.app.data.local.database.FakeUserDao
import com.piroworkz.architectcoders.app.data.local.database.StoreDao
import com.piroworkz.architectcoders.app.data.local.database.UserDao
import com.piroworkz.architectcoders.app.data.local.datasource.StoreRoomDataSource
import com.piroworkz.architectcoders.app.data.local.datasource.UserRoomDataSource
import com.piroworkz.architectcoders.app.data.local.entities.DbUserProfile
import com.piroworkz.architectcoders.app.data.remote.datasource.FakeFirebaseAuthDataSource
import com.piroworkz.architectcoders.data.remote.datasource.FakeStoreFireStoreDataSource
import com.piroworkz.architectcoders.data.remote.datasource.FakeUserFireStoreDataSource
import com.piroworkz.architectcoders.app.data.utils.toDatabase
import com.piroworkz.architectcoders.app.data.utils.toDatabaseProduct
import com.piroworkz.architectcoders.app.data.utils.toDbCartItem
import com.piroworkz.architectcoders.data.StoreRepository
import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.data.source.*
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.domain.UserProfile

fun buildStoreRepository(
    product: List<Product> = emptyList(),
    shoppingCart: List<CartItem> = emptyList()
): StoreRepository {
    val storeDao: StoreDao =
        FakeStoreDao(
            defaultProducts = product.toDatabaseProduct().toMutableList(),
            defaultShoppingCart = shoppingCart.map { it.toDbCartItem() }.toMutableList()
        )
    val remoteDataSource: StoreRemoteDataSource = FakeStoreFireStoreDataSource()
    val localDataSource: StoreLocalDataSource = StoreRoomDataSource(storeDao)
    return StoreRepository(remoteDataSource, localDataSource)
}

fun buildUserRepository(
    isLoggedIn: Boolean = true,
    userProfile: UserProfile,
    authProvider: String = "Facebook"
): UserRepository {
    val userDao: UserDao = FakeUserDao(userProfile.toDatabase())

    val remoteDataSource: UserRemoteDataSource =
        FakeUserFireStoreDataSource()

    val localDataSource: UserLocalDataSource =
        UserRoomDataSource(userDao)

    val authenticationDatasource: AuthenticationDatasource =
        FakeFirebaseAuthDataSource(isLoggedIn, authProvider)

    return UserRepository(remoteDataSource, localDataSource, authenticationDatasource)
}

private fun UserProfile.toDatabase(): DbUserProfile {
    return DbUserProfile(
        user?.toDatabase(),
        shippingAddress?.toDatabase(),
        billingAddress?.toDatabase(),
        paymentMethod?.toDatabase()
    )
}
