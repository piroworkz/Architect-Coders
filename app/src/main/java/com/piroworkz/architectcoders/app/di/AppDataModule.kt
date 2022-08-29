package com.piroworkz.architectcoders.app.di

import com.piroworkz.architectcoders.app.data.AndroidPermissionChecker
import com.piroworkz.architectcoders.app.data.GMSLocationDataSource
import com.piroworkz.architectcoders.app.data.remote.datasource.FirebaseAuthDataSource
import com.piroworkz.architectcoders.app.data.remote.datasource.GeoNamesServerDataSource
import com.piroworkz.architectcoders.app.data.remote.datasource.StoreFireStoreDataSource
import com.piroworkz.architectcoders.app.data.remote.datasource.UserFireStoreDataSource
import com.piroworkz.architectcoders.app.data.local.datasource.StoreRoomDataSource
import com.piroworkz.architectcoders.app.data.local.datasource.UserRoomDataSource
import com.piroworkz.architectcoders.data.source.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindStoreFireStoreDataSource(storeFireStoreDataSource: StoreFireStoreDataSource): StoreRemoteDataSource

    @Binds
    abstract fun bindStoreRoomDataSource(storeRoomDataSource: StoreRoomDataSource): StoreLocalDataSource

    @Binds
    abstract fun bindUserRoomDataSource(userRoomDataSource: UserRoomDataSource): UserLocalDataSource

    @Binds
    abstract fun bindUserFireStoreDataSource(userFireStoreDataSource: UserFireStoreDataSource): UserRemoteDataSource

    @Binds
    abstract fun bindFirebaseAuthDataSource(firebaseAuthDataSource: FirebaseAuthDataSource): AuthenticationDatasource

    @Binds
    abstract fun bindGeoNamesServerDataSource(geoNamesServerDataSource: GeoNamesServerDataSource): PostalCodesDataSource

    @Binds
    abstract fun bindGMSLocationDataSource(gmsLocationDataSource: GMSLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindAndroidPermissionChecker(androidPermissionChecker: AndroidPermissionChecker): PermissionChecker

}