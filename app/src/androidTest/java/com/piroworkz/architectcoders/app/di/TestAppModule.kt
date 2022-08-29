package com.piroworkz.architectcoders.app.di

import android.app.Application
import android.location.Geocoder
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.data.local.database.AppDatabase
import com.piroworkz.architectcoders.app.data.local.database.StoreDao
import com.piroworkz.architectcoders.app.data.local.database.UserDao
import com.piroworkz.architectcoders.app.data.remote.GeoService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class])
object TestAppModule {

    @Singleton
    @Provides
    @GeoBaseUrl
    fun provideBaseUrl(): String = "http://localhost:9000"

    @Singleton
    @Provides
    @GeoUserName
    fun provideGeoUserName(application: Application): String =
        application.getString(R.string.geo_user_name)

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao

    @Singleton
    @Provides
    fun provideStoreDao(database: AppDatabase): StoreDao = database.storeDao

    @Singleton
    @Provides
    fun provideOkHttp3Client(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Singleton
    @Provides
    fun provideGeoService(@GeoBaseUrl baseUrl: String, client: OkHttpClient): GeoService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(baseUrl)
            .build().create(GeoService::class.java)

    @Provides
    fun provideFusedLocationClient(application: Application): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    @Provides
    fun provideGeoCOder(application: Application): Geocoder =
        Geocoder(application)
}